public class Cella
{
	private final int MAX = 5 ;					// numero masssimo di pedine che possono essere inseriti in una cella
	private Pedina[] personaggi ;				// array contenente le pedine inserite
	private int n ; 							// numero di personaggi inseriti nella cella
	private int riga ;							// numero di riga
	private int colonna ;						// numero di colonna
	private String bioma ;						// tipologia ambientale della cella
	private boolean giorno ;					// riferimento temporale della cella
	
	public Cella(int x, int y)
	{
		riga = x ;
		colonna = y ;
		n = 0 ;
		personaggi = new Pedina[MAX] ;
		bioma = casuale() ;
		giorno = true ;
	}
	
	public int get_riga()
	{
		return riga ;
	}
	
	public int get_colonna()
	{
		return colonna ;
	}
	
	public void add_Pedina(String tipo) throws ArrayIndexOutOfBoundsException, IllegalArgumentException			// metodo per aggiungere pedine alla cella
	{	
		try{
			if(!tipo.equals("elfo") && !tipo.equals("nano") && !tipo.equals("orco"))							// se il tipo non è valido, lancia eccezione
				throw new IllegalArgumentException() ;
		}
		catch(IllegalArgumentException exc){
			System.out.println("Tipo inserito non valido: deve essere elfo, nano o orco.") ;
			System.exit(0) ;
		}
		
		try{
			if(tipo.equals("elfo"))																		// se il tipo è valido, inserisce la pedina e aggiorna n
			{
				personaggi[n] = new Elfo() ;
			}
			
			if(tipo.equals("nano"))
			{
				personaggi[n] = new Nano() ;
			}
		
			if(tipo.equals("orco"))
			{
				personaggi[n] = new Orco() ;
			}
			
			n++ ;
		}
		catch(ArrayIndexOutOfBoundsException exc){														// se n arriva a 5, lancia eccezione
			System.out.println("Puoi inserire al massimo 5 pedine in una casella.") ;
			System.exit(0) ;
		}
	}
	
	public int get_Elfi()						// ritorna il numero di elfi presenti nella cella
	{
		return get_NumeroDi("Elfi") ;	
	}
	
	public int get_Nani()						// ritorna il numero di nani presenti nella cella
	{
		return get_NumeroDi("Nani") ;
	}
	
	public int get_Orchi()						// ritorna il numero di orchi presenti nella cella
	{
		return get_NumeroDi("Orchi") ;
	}
	
	public double get_DifesaGiorno() 			// ritorna il valore di difesa di giorno della cella
	{
		double valore = 0 ;

		for(int i = 0; i < n; i++)
			valore = valore + personaggi[i].get_Difesa(bioma, giorno) ;
		
		return valore ;
	}
	
	public double get_AttaccoGiorno()			// ritorna il valore di attacco di giorno della cella
	{
		double valore = 0 ;
		
		for(int i = 0; i < n; i++)
			valore = valore + personaggi[i].get_Attacco(bioma, giorno) ;
		
		return valore ;
	}
	
	public double get_DifesaNotte()				// ritorna il valore di difesa di notte della cella
	{
		double valore = 0 ; 
		
		for(int i = 0; i < n; i++)
			valore = valore + personaggi[i].get_Difesa(bioma, !giorno) ;
		
		return valore ;
	}
	
	public double get_AttaccoNotte()			// ritorna il valore di attacco di notte della cella
	{
		double valore = 0 ;
		
		for(int i = 0; i < n; i++)
			valore = valore + personaggi[i].get_Attacco(bioma, !giorno) ;
		
		return valore ;
	}
			
	private int get_NumeroDi(String tipo)
	{
		
		if(n == 0)								// se non ci sono pedine nella cella, ritorna 0
			return 0 ;
		
		int numero = 0 ;
		
		if(tipo.equals("Elfi"))					// se il tipo è "Elfi", calcola il numero di elfi presenti nella cella
		{
			for(int i = 0; i < n; i++)
			{
				if(personaggi[i].getClass().equals(Elfo.class))
					numero++ ;
			}
			return numero ;
		}
		
		if(tipo.equals("Nani"))					// se il tipo è "Nani", calcola il numero di nani presenti nella cella
		{
			for(int i = 0; i < n; i++)
			{
				if(personaggi[i].getClass().equals(Nano.class))
					numero++ ;
			}
			return numero ;
		}
		
		if(tipo.equals("Orchi"))				// se il tipo è "Orchi", calcola il numero di orchi presenti nella cella
		{
			for(int i = 0; i < n; i++) 
			{
				if(personaggi[i].getClass().equals(Orco.class)) 
					numero++ ;
			}
			return numero ;
		}
		
		return 0 ;
	}
	
	private String casuale()								// metodo che per assegnare casualmente la tipologia ambientale della cella
	{
		int caso = (int)(Math.random() * 10) ;
		String ambiente ;
		
		if(caso >= 0 && caso <= 3)
		 	ambiente = "Pianura" ;
		else if(caso >= 4 && caso <= 6)
			ambiente = "Bosco" ;
		else 
			ambiente = "Montagna" ;
		
		return ambiente ;
	}
}	