public class Nano implements Pedina
{
	private double attacco ;
	private double difesa ;
	private double modificatore_attacco ;
	private double modificatore_difesa ;
	
	public Nano()
	{
		attacco = 2 ;
		difesa = 5 ;
		modificatore_attacco = attacco * 1 ;
		modificatore_difesa = difesa * 0 ;
	}
	
	public double get_Attacco(String condizione1, boolean condizione2)
	{
		if(condizione1.equals("Montagna"))
			return attacco + modificatore_attacco ;
		else
			return attacco ;
	}
	
	public double get_Difesa(String condizione1, boolean condizione2)
	{
		if(condizione1.equals("Montagna"))
			return difesa + modificatore_difesa ;
		else
			return difesa ;
	}
}