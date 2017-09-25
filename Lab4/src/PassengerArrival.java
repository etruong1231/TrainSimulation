//Class used to represents the passenger arrival behavior extracted from Train Config
//Eddie Truong 18063651
//Vatsal Rustagi 41346390
public class PassengerArrival
{
	private int intialTrain;
	private int numPassengers;
	private int destinationTrain;
	private int timePeriod;
	private int expectedTimeOfArrival;
	
	public PassengerArrival(int newIntialTrain, int newNumPassengers,
		int newDestinationTrain, int newTimePeriod, int expectedTime)
	{
		intialTrain = newIntialTrain;
		numPassengers = newNumPassengers;
		destinationTrain = newDestinationTrain;
		timePeriod = newTimePeriod;
		expectedTimeOfArrival = expectedTime;
		
	}
	
	//getters 
	public int getTimePeriod()
	{
		return timePeriod;
	}
	
	public int getnumPassengers()
	{
		return numPassengers;
	}
	public int getIntialStation()
	{
		return intialTrain;
	}
	public int getDestinationTrain()
	{
		return destinationTrain;
	}
	public int getExpectedTime()
	{
		return expectedTimeOfArrival;
	}
	
}
