//Clsas that allows trains to manipulate the state of the train stations. 
//Eddie Truong 18063651
//Vatsal Rustagi 41346390
public class TrainSystemManager
{
	private TrainStation [] trainStation = new TrainStation[5];
	
	
	public TrainSystemManager()
	{	//set up the train stations
		for(int i =0 ; i <5; i++)
		{
			TrainStation stations = new TrainStation();
			trainStation[i] = stations;
		}
	}
	
	public void setUp(int IntialStation, int numPassengers, int destinationStation, int timePeriod)
	
	{
		//adds the trainstation passengerRequests
		trainStation[IntialStation].addPassengerRequest(destinationStation, numPassengers);
		//increments the train station totaldestinationrequests 
		trainStation[IntialStation].addDestinationRequests(destinationStation, numPassengers);
	
	}
	
	
	public int checkForWaitingStation(int train)
	{
		for(int i= 0; i < 5; i++)
		{
			if(trainStation[i].checkForPassengers(i))
			{	//locks the trainStation to check if a train is going to it
				synchronized(this)
				{		//check if any train is goiing to it
						if(!trainStation[i].trainApproachingCheck())
						{	//if no train is going to that station, set that train to going to it
							trainStation[i].trainApproaching(train);
							return i;
						}
							
				}
			}
		}
		return -1;
	}
	
	public int getPassengerDestinationUp(int Station)
	{
		return trainStation[Station].getPassengerDestinationGoingUp(Station);
	}
	
	
	public int getPassengerDestinationDown(int Station)
	{
		return trainStation[Station].getPassengerDestinationGoingDown(Station);
	}
	
	//set the station incoming train to nothing
	public void setNoIncomingTrain(int station)
	{
		synchronized(this)
		{trainStation[station].trainApproaching(-1);
		//System.out.println("Station "+ station+ " is unlocked");
		}
	}
	
	public int getPassengerAmount(int station, int destination)
	{
		return trainStation[station].loadingPassengers(destination);
	}
	
	//prints out the statistics from all the train station
	public void printStatistics()
	{
		for(int i = 0 ; i < 5 ; i++)
		{
			trainStation[i].printStationStatistics(i);
		}
	}
	
	
}
