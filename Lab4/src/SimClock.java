//Clock class
//Eddie Truong 18063651
//Vatsal Rustagi 41346390
public class SimClock
{
	private static int simulatedTime;
	
	public SimClock()
	{
		simulatedTime = 0;
	}
	
	//increments the time by 1
	public static void tick()
	{
		simulatedTime += 1;
	}
	
	public static int getTime()
	{
		return simulatedTime;
	}
	
	
}
