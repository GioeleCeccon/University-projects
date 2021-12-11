public class Mappa
{
	private Cella[][] mappa ; 					// matrice che costituisce la griglia della mappa
	private int M ;								// numero di righe
	private int N ;								// numero di colonne
	
	public Mappa(int righe, int colonne) throws NegativeArraySizeException
	{	
		M = righe ;
		N = colonne ;
		
		try {															// se M o N ha valori negativi, lancia eccezione
			mappa = new Cella[M][N] ;
		}
		catch (NegativeArraySizeException exc) {
			System.out.println("Le coordinate della mappa non possono assumere valori minori di 0.") ;
			System.exit(0) ;
		}
		
		for(int i = 0; i < M; i++)										// inizializzazione di tutte le celle della matrice
		{
			for(int j = 0; j < N; j++)
				mappa[i][j] = new Cella(i, j) ;
		}
	}
	
	public void add(int x, int y, String tipo) throws ArrayIndexOutOfBoundsException		// metodo per aggiungere pedine alla mappa
	{
		try{
			mappa[x][y].add_Pedina(tipo.toLowerCase()) ;
		}
		catch(ArrayIndexOutOfBoundsException exc){											// se si inseriscono pedine al di fuori della mappa (x,y < 0 o x >= M o Y >= N) lancia eccezione
			System.out.println("Hai inserito delle pedine fuori dalla mappa.") ;
			System.exit(0) ;
		}
	}
	
	public int get_ElfiTotali()					// ritorna il numero totale di elfi presenti nella mappa, sommando i valori delle singole celle
	{
		int numero = 0;
		
		for(int i = 0; i < M; i++)
		{
			for(int j = 0; j < N; j++)
				numero = numero + mappa[i][j].get_Elfi() ;
		}
		
		return numero ;
	}
	
	public int get_NaniTotali()					// ritorna il numero totale di nani presenti nella mappa, sommando i valori delle singole celle
	{
		int numero = 0 ;
		
		for(int i = 0; i < M; i++)
		{
			for(int j = 0; j < N; j++)
				numero = numero + mappa[i][j].get_Nani() ;
		}
		
		return numero ;
	}
	
	public int get_OrchiTotali()				// ritorna il numero totale di orchi presenti nella mappa, sommando i valori delle singole celle
	{
		int numero = 0 ;
		
		for(int i = 0; i < M; i++)
		{
			for(int j = 0; j < N; j++)
				numero = numero + mappa[i][j].get_Orchi() ;
		}
		
		return numero ;
	}

	public void get_CellaElfi()					// stampa le coordinate della cella o delle celle che presentano il numero massimo di elfi 
	{	
		int max = 0 ;
		
		int[] valori = new int[M * N] ;			
		int k = 0 ;
		
		for(int i = 0; i < M; i++)
		{
			for(int j = 0; j < N; j++)
			{
				valori[k] = mappa[i][j].get_Elfi() ;		// memorizzo tutti i valori delle singole celle
				k++ ;
			}
		}
		
		max = get_maxI(valori) ;							// trovo il valore massimo
			
		if(max == 0)										// se il valore massimo è = 0, stampo
			System.out.print("non sono presenti elfi.") ;
		else												// altrimenti, stampo le coordinate della cella o delle celle il cui numero di elfi è = valore massimo
		{
			for(int i = 0; i < M; i++)
			{
				for(int j = 0; j < N; j++)
				{
					if(mappa[i][j].get_Elfi() == max)
						System.out.print("(" + mappa[i][j].get_riga() + "," + mappa[i][j].get_colonna() + ") ") ;
				}
			}
		}
	}
	
	public void get_CellaNani()								// stampa le coordinate della cella o delle celle che presentano il numero massimo di nani
	{
		int max = 0 ;
		
		int[] valori = new int[M * N] ;
		int z = 0 ;
		
		for(int i = 0; i < M; i++)			
		{
			for(int j = 0; j < N; j++)
			{
				valori[z] = (mappa[i][j]).get_Nani() ;		// memorizzo tutti i valori delle singole celle
				z++ ;
			}
		}
		
		max = get_maxI(valori) ;							// trovo il valore massimo
		
		if(max == 0)										// se il valore massimo è = 0, stampo
			System.out.print("non sono presenti nani.") ;
		else												// altrimenti, stampo le coordinate della cella o delle celle il cui numero di nani è = valore massimo
		{
			for(int i = 0; i < M; i++)
			{
				for(int j = 0; j < N; j++)
				{
					if(mappa[i][j].get_Nani() == max)
						System.out.print("(" + mappa[i][j].get_riga() + "," + mappa[i][j].get_colonna() + ") ") ;
				}
			}
		}
	}
	
	public void get_CellaOrchi()							// stampa le coordinate della cella o delle celle che presentano il numero massimo di orchi
	{
		int max = 0 ;
		
		int[] valori = new int[M * N] ;
		int z = 0 ;
		
		for(int i = 0; i < M; i++)
		{
			for(int j = 0; j < N; j++)
			{
				valori[z] = (mappa[i][j]).get_Orchi() ;		// memorizzo tutti i valori delle singole celle
				z++ ;
			}
		}
		
		max = get_maxI(valori) ;							// trovo il valore massimo
		
		if(max == 0)										// se il valore massimo è = 0, stampo
			System.out.print("non sono presenti orchi.") ;
		else												// altrimenti, stampo le coordinate della cella o delle celle il cui numero di orchi è = valore massimo
		{
			for(int i = 0; i < M; i++)
			{
				for(int j = 0; j < N; j++)
				{
					if(mappa[i][j].get_Orchi() == max)
						System.out.print("(" + mappa[i][j].get_riga() + "," + mappa[i][j].get_colonna() + ") ") ;
				}
			}
		}
	}
	
	public void get_CellaAttGiorno()						// stampa le coordinate della cella o delle celle che presentano il valore massimo di attacco di giorno
	{
		double max = 0 ;
		
		double[] valori = new double[M * N] ;
		int z = 0 ;
		
		for(int i = 0; i < M; i++)
		{
			for(int j = 0; j < N; j++)
			{
				valori[z] = (mappa[i][j]).get_AttaccoGiorno() ;		// memorizzo tutti i valori delle singole celle
				z++ ;
			}
		}
		
		max = get_maxD(valori) ;									// trovo il valore massimo
	
		for(int i = 0; i < M; i++)									// stampo le coordinate della cella o delle celle il cui valore massimo di attacco di giorno = valore massimo
		{
			for(int j = 0; j < N; j++)
			{
				if(mappa[i][j].get_AttaccoGiorno() == max)
					System.out.print("(" + mappa[i][j].get_riga() + "," + mappa[i][j].get_colonna() + ") ") ;
			}
		}	
	}
	
	public void get_CellaAttNotte()							// stampa le coordinate della cella o delle celle che presentano il valore massimo di attacco di notte
	{
		double max = 0 ;
		
		double[] valori = new double[M * N] ;
		int z = 0 ;
		
		for(int i = 0; i < M; i++)
		{
			for(int j = 0; j < N; j++)
			{
				valori[z] = (mappa[i][j]).get_AttaccoNotte() ;		// memorizzo tutti i valori delle singole celle
				z++ ;
			}
		}
		
		max = get_maxD(valori) ;									// trovo il valore massimo
		
		for(int i = 0; i < M; i++)									// stampo le coordinate della cella o delle celle il cui valore massimo di attacco di notte = valore massimo
		{
			for(int j = 0; j < N; j++)
			{
				if(mappa[i][j].get_AttaccoNotte() == max)
					System.out.print("(" + mappa[i][j].get_riga() + "," + mappa[i][j].get_colonna() + ") ") ;
			}
		}
	}
	
	public void get_CellaDifGiorno()						// stampa le coordinate della cella o delle celle che presentano il valore massimo di difesa di giorno
	{
		double max = 0 ;
		
		double[] valori = new double[M * N] ;
		int z = 0 ;
		
		for(int i = 0; i < M; i++)
		{
			for(int j = 0; j < N; j++)
			{
				valori[z] = (mappa[i][j]).get_DifesaGiorno() ;		// memorizzo tutti i valori delle singole celle
				z++ ;
			}
		}
		
		max = get_maxD(valori) ;									// trovo il valore massimo
		
		for(int i = 0; i < M; i++)									// stampo le coordinate della cella o delle celle il cui valore massimo di difesa di giorno = valore massimo
		{
			for(int j = 0; j < N; j++)
			{
				if(mappa[i][j].get_DifesaGiorno() == max)
					System.out.print("(" + mappa[i][j].get_riga() + "," + mappa[i][j].get_colonna() + ") ") ;
			}
		}	 
	}
	
	public void get_CellaDifNotte()							// stampa le coordinate della cella o delle celle che presentano il valore massimo di difesa di giorno
	{
		double max = 0 ;
		
		double[] valori = new double[M * N] ;
		int z = 0 ;
		
		for(int i = 0; i < M; i++)
		{
			for(int j = 0; j < N; j++)
			{
				valori[z] = (mappa[i][j]).get_DifesaNotte() ;	// memorizzo tutti i valori delle singole celle
				z++ ;
			}
		}
		
		max = get_maxD(valori) ;								// trovo il valore massimo
		
		for(int i = 0; i < M; i++)								// stampo le coordinate della cella o delle celle il cui valore massimo di difesa di notte = valore massimo
		{
			for(int j = 0; j < N; j++)
			{
				if(mappa[i][j].get_DifesaNotte() == max)
					System.out.print("(" + mappa[i][j].get_riga() + "," + mappa[i][j].get_colonna() + ") ") ;
			}
		}
	}
		
	private int get_maxI(int[] array)				// metodo per trovare il valore massimo in un array di int
	{
		int massimo = array[0];

		for(int i = 0; i < array.length; i++) 
		{
			if(array[i] > massimo) 
				massimo = array[i] ;
		}
		
		return massimo ;
	}		
		
	private double get_maxD(double[] array)			// metodo per trovare il valore massimo in un array di double
	{
		double massimo = array[0];

		for(int i = 0; i < array.length; i++) 
		{
			if(array[i] > massimo) 
				massimo = array[i] ;
		}
		
		return massimo ;
	}
}
		
		
		
		
		