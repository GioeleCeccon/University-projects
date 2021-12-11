public class Orco implements Pedina
{
	private double attacco ;
	private double difesa ;
	private double modificatore_attacco ;
	private double modificatore_difesa ;
	
	public Orco()
	{
		attacco = 4 ;
		difesa = 4 ;
		modificatore_attacco = attacco * 0.5 ;
		modificatore_difesa = difesa * 0.5 ;
	}
	
	public double get_Attacco(String condizione1, boolean condizione2)
	{
		if(condizione2 == true)
			return attacco - modificatore_attacco ;
		else
			return attacco + modificatore_attacco ;
	}
	
	public double get_Difesa(String condizione1, boolean condizione2)
	{
		if(condizione2 == true)
			return difesa - modificatore_difesa ;
		else
			return difesa + modificatore_difesa ;
	}
}