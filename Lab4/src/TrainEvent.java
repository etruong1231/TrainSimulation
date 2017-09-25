//a class representing an event that the train is scheduled to perform 
//conatins information include expected time that the event will occur
//Eddie Truong 18063651
//Vatsal Rustagi 41346390
public class TrainEvent
{
	private int destination;
	private int expectedArrival;
	
	public TrainEvent(int destinationStation, int ArrivalTime)
	{
		destination = destinationStation;
		expectedArrival = ArrivalTime;
	}
	
	public int getDestination()
	{
		return destination;
	}
	public int getExpectedArrivalTime()
	{
		return expectedArrival;
	}
}
