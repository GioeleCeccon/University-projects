%Questo metodo ha lo scopo di codificare una proteina, partendo dalla
%sequenza di amino-acidi passata come parametro.
%Il metodo fornirà come output un vettore ("codifica"), di lunghezza
%fisssa pari a 20, che rappresenta la singola proteina.
function codifica=Descrittore(Proteina)

%in KeySet son contenute le chiavi del dizionario, che servirà poi 
%per ottenere l'indice del dato amino-acido all'interno del vettore
%di lunghezza fissa
keySet={'A','C','D','E','F','G','H','I','K','L','M','N','P','Q','R','S','T','V','W','Y'};
%vettore di 20 elementi che rappresenta la codifica numerica della proteina
%ogni elemento viene inzializzato a 0
codifica=zeros(20,1);
codifica([1:20])=0;

%vettore di 20 elementi che rappresenta l'indice del dato amino-acido all'
%interno del vettore codifica. Ad ogni elemento del vettore viene assegnato un
%valore pari all'indice della propria posizione.
value=[1:20];

%Struttura dati che contiene come chiavi la lista degli amino-acidi e
%come valore l'indice del dato amino-acido all'interno del vettore codifica
%esempio: M('A')=1; M('Y')=20;
M=containers.Map(keySet,value);

%Itera l'intera proteina
%per ogni amino-acido somma all'elmento corrispondente nel vettore
%codifica, il rapporto dato da:
%posizione di tale amino-acido all'interno della proteina e la lunghezza di
%tale proteina
for i=1:strlength(Proteina)
  codifica(M(Proteina(i)))=codifica(M(Proteina(i)))+1;


end
for i=1:20
  codifica(i)=codifica(i)/strlength(Proteina);


end
end