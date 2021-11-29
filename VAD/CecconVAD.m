% Ogni pacchetto corrisponde a 20 ms ed è costituito da 160 campioni audio.
% Ogni campione è rappresentato da un intero a 8 bit con segno.
% Considero l’intervallo di quantizzazione come adattato all’ampiezza del
% segnale, sicché i valori quantizzati spaziano tendenzialmente sull’intero
% intervallo.
% Per ogni pacchetto, l'algoritmo di VAD deve dire se il pacchetto è
% significativo, ossia contiene voce, (output 1) o meno (output 0).


% Nomi dei file di input e di output. Il file di input deve trovarsi nella
% stessa directory di questo file
nomeInput = "inputaudio.data";
nomeOutput = "outputVAD.txt";
    
% Apro il file di input in modalità lettura e il file di output
% in modalità scrittura
fileID_in = fopen(nomeInput,'r');
fileID_out = fopen(nomeOutput,'w');
    
% Leggo il file di input: di default, fread legge 1 byte alla volta e
% li inserisce in un vettore colonna contenente il valore int del
% singolo byte
% inf mi permette di leggere il file fino alla fine
% 'int8' mi permette di considerare il segno
values = fread(fileID_in,Inf,'int8');

% Frequenza di campionamento [Hz]
F = 8000;
% Periodo di campionamento [s]
T = 1/F;
% Lunghezza del segnale [byte]
L = length(values);
% Vettore tempo della traccia audio
t = 0:T:(L * T) - T;
% Dimensione di un pacchetto [byte]
dim = 160;
% Numero totale di pacchetti nel file: la funzione floor() arrotonda
% per difetto
totale = floor(L/dim);


% Imposto i limiti iniziali
EN_thresh = 40;        % Threshold per l'energia 
domF_thresh = 0.55;    % Threshold per la frequenza dominante
SF_thresh = 0.5;       % Threshold per la spectral flatness

% Array in cui salvo i valori calcolati di ogni pacchetto
energie = zeros(totale,1);
frequenze = zeros(totale,1);
spettri = zeros(totale,1);
    
% Valori minimi, utilizzati nel calcolo
min_E = 40;     % Energia 	
min_F = 185;    % Frquenza dominante
min_S = 5;      % Spectral flatness

% Array in cui salvo i risultati del VAD per ogni pacchetto
risultati = zeros(totale,1);

% Variabile "di supporto" utilizzata per aggiornare il threshold
% dell'energia
silence_count = 0;

% Analizzo tutti i pacchetti di ogni traccia uno alla volta
for i = 1:totale

    % Calcolo l'energia dell'i-esimo pacchetto
    energia = 0;
    for j = 1:dim
        primo = (i-1)*dim + j;
        energia = energia + (values(primo)^2)/dim;
    end

    energie(i) = energia;
        
    % Analisi attraverso la frequenza del pacchetto i + pacchetto i+1.
    % L'utlimo pacchetto invece viene analizzato singolarmente
    % Codice per la gestione della trasformata fornito da MATLAB
    if i == totale
        X = values(dim*(i-1)+1:dim*(i));
    else
        X = values(dim*(i-1)+1:dim*(i+1));
    end
    
    L = length(X);
    Y = fft(X);
    % Elimino la simmetria della trasformata e adatto alla lunghezza
    P2 = abs(Y/L);
    P1 = P2(1:L/2+1);
    P1(2:end-1) = 2*P1(2:end-1);    
    f = F*(0:(L/2))/L ;

    % Frequenza massima (dominante) nel pacchetto. Per ottenerla
    % utilizzo solo le frequenze che si trovano nell'intorno dei 200
    % Hz, in quanto mi interessa rilevare la voce umana
    under = round(0.01*L);
    over = round(0.06*L);
    domF = max(P1(under:over));

    frequenze(i) = domF;

    % Calcolo della spectral flatness
    sf = (exp(sum(log(P1.^2))/length((P1.^2))))/(sum(P1.^2)/length((P1.^2)));
        
    spettri(i) = sf;
        
    % Utilizzo i primi 100 ms per impostare i valoriminimi
    % "sperimentali" e aggiorno il threshold dell'energia
    if i <= 5
        min_E = min(energie(i), min_E);
        min_F = min(frequenze(i),min_F);
        min_S = min(spettri(i),min_S);
    
        EN_thresh = EN_thresh*log10(min_E);
    end
        
    % Array in cui salvo i risultati dei test. Ad ogni iterazione del
    % ciclo (quindi ad ogni pacchetto) viene reimpostato a [0,0,0]
    counter = zeros(3,1);

    % Eseguo i test e salvo i valori dei risultati in counter
    if energie(i) - min_E > EN_thresh
        counter(1) = 1;
    end
        
    if frequenze(i) - min_F >= domF_thresh    
        counter(2) = 1;
    end
        
    if spettri(i) - min_S >= SF_thresh      
        counter(3) = 1;
    end
        
    % Decido l'output dell'algoritmo
    % Se qualche test è superato, elaboro
    if sum(counter) > 1
        if counter(1) == 1
            if counter(2) == 1
                risultati(i) = 1;
            end
        elseif counter(2) == 1
            risultati(i) = 1;
        elseif counter(3) == 1
            risultati(i) = 1;
        end   
    % Altrimenti aggiorno il threshold dell'energia
    else
        silence_count = silence_count + 1;
        min_E = ((silence_count*min_E) + energie(i))/(silence_count + 1);
        EN_thresh = 40*log10(min_E);
    end

    % Stampo l'output del VAD per il singolo pacchetto
    fprintf(fileID_out,"%i",risultati(i));
   
end

% Chiudo file di input e di output
fclose(fileID_in);
fclose(fileID_out);
    
    
    figure
    nexttile
    plot(t,values)
    hold on
    
    x2 = 1:numel(risultati);
    x2 = x2.*(t(end)/numel(risultati));
    
    plot(x2, risultati*100);
    
