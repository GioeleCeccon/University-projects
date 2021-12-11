public class Elfo implements Pedina
{
	private double attacco ;
	private double difesa ;
	private double modificatore_attacco ;
	private double modificatore_difesa ;
	
	public Elfo()
	{
		attacco = 5 ;
		difesa = 2 ;
		modificatore_attacco = attacco * 0 ;
		modificatore_difesa = difesa * 1 ;
	}
	
	public double get_Attacco(String condizione1, boolean condizione2)
	{
		if(condizione1.equals("Bosco"))
			return attacco + modificatore_attacco ;
		else
			return attacco ;
	}
	
	public double get_Difesa(String condizione1, boolean condizione2)
	{
		if(condizione1.equals("Bosco"))
			return difesa + modificatore_difesa ;
		else
			return difesa ;
	}
}