import java.io.*;
import java.util.Scanner ;
import java.util.NoSuchElementException ;
import java.util.InputMismatchException ;

public class Main
{
	public static void main(String[] args)  throws FileNotFoundException, InputMismatchException, Exception
	{
		int righe ;
		int colonne ;
		
		try{
			System.out.println("Ciao, benvenuto in questa nuova avventura!") ;
			System.out.println("Per favore, inserisci i valori per determinare la dimensione della mappa:") ;
			
			Scanner in = new Scanner(System.in) ;
			
			System.out.print("- numero di righe: ") ;
			righe = in.nextInt() ;								// ricevo Numero di righe
			
			System.out.print("- numero di colonne: ") ;
			colonne = in.nextInt() ;							// ricevo Numero di colonne
		
			Mappa mappa = new Mappa(righe, colonne) ;			// creo la mappa
			
			Scanner file = new Scanner(new FileReader("Partita.txt")) ;			
			
			while(file.hasNextLine())							// leggo tutto il file
			{		
				String line1 = file.nextLine().trim() ;
				if(line1.equals(""))							// se la prima riga è uguale a riga vuota, allora salvo in line1 la riga successiva: mi permette di supportare entrambi i formati indicati dai committenti
					line1 = file.nextLine().trim() ;					// non si devono lasciare righe vuote dopo l'ultimo inserimento 
				
				Scanner s1 = new Scanner(line1) ;
				int x = Integer.parseInt(s1.next().trim()) ;			// leggo la prima coordinata
				s1.close() ;
			
				Scanner s2 = new Scanner(file.nextLine().trim()) ;
				int y = Integer.parseInt(s2.next()) ;			// leggo la seconda coordinata
				s2.close() ;
				
				String tipo = file.nextLine().trim() ;					// leggo il tipo della pedina
			
				mappa.add(x, y, tipo) ;							// aggiungo le pedine alle celle della mappa
			}
			
			in.close() ;										
			file.close() ;
		
			System.out.println("") ;
			
			System.out.println("Ecco i tuoi calcoli!") ;		// stampo tutti i calcoli 
			System.out.println("") ;
			
			System.out.println("Il numero di elfi presenti è: " + mappa.get_ElfiTotali()) ;
			System.out.println("Il numero di nani presenti è: " + mappa.get_NaniTotali()) ;
			System.out.println("Il numero di orchi presenti è: " + mappa.get_OrchiTotali()) ;
			System.out.println("") ;
			
		
			System.out.print("La casella|Le caselle col maggior valore di difesa di giorno: ") ;
			mappa.get_CellaDifGiorno() ;
			System.out.println("") ;
			System.out.println("") ;
		
			System.out.print("La casella|Le caselle col maggior valore di difesa di notte: ") ;
			mappa.get_CellaDifNotte() ;
			System.out.println("") ;
			System.out.println("") ;
		
			System.out.print("La casella|Le caselle col maggior valore di attacco di giorno: ") ;
			mappa.get_CellaAttGiorno() ;
			System.out.println("") ;
			System.out.println("") ;
		
			System.out.print("La casella|Le caselle col maggior valore di attacco di notte: ") ;
			mappa.get_CellaAttNotte() ;
			System.out.println("") ;
			System.out.println("") ;
		
			System.out.print("La casella|Le caselle col maggior numero di elfi: ") ;
			mappa.get_CellaElfi() ;
			System.out.println("") ;
			System.out.println("") ;
			
			System.out.print("La casella|Le caselle col maggior numero di nani: ") ;
			mappa.get_CellaNani() ;
			System.out.println("") ;
			System.out.println("") ;
		
			System.out.print("La casella|Le caselle col maggior numero di orchi: ") ;
			mappa.get_CellaOrchi() ;
			System.out.println("") ;
			System.out.println("") ;
		}
		catch(FileNotFoundException exc){							
			System.out.println("Il file Partita.txt deve trovarsi nella cartella del sistema.") ;
			System.exit(0) ;
		}
		catch(InputMismatchException exc){
			System.out.println("Devi inserire numeri interi.") ;
			System.exit(0) ;
		}
		catch(Exception exc){
			System.out.println("Errore di formato: non puoi lasciare spazi vuoti dopo l'ultimo inserimento, le coordinate sono dei numeri interi.") ;
			System.exit(0) ;
		}	
	}
}