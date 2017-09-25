//Class that represents the state of a specific train station in the train system
//Eddie Truong 18063651
//Vatsal Rustagi 41346390
public class TrainStation
{
	private int[] totalDestinationRequests = new int[5];
	private int[] arrivedPassengers = new int[5];
	private int[] passengerRequests = new int[5];
	private int approachingTrain = -1;
	
	//checks if the station has passengers Going up
	public boolean checkForPassengers(int thisStation)
	{
		for(int i = 0; i < 5; i++)
		{
			if (passengerRequests[i] > 0 && approachingTrain == -1)
				return true;
		}
		return false;
	}
	
	public int getPassengerDestinationGoingUp(int thisStation)
	{
		for(int i = 0; i < 5; i++)
		{
			if (passengerRequests[i] > 0 && thisStation < i )
				{	
					return i;
				}
		}
		return -1;
	}

	//gets the requested station going down if none going up
	public int getPassengerDestinationGoingDown(int thisStation)
	{
		for(int i = 4; i >= 0; i--)
		{
			if (passengerRequests[i] > 0 && thisStation > i)
				{	
					return i;
				}
		}
		return -1;
	}

	//add the amount of passengers request currently to a station from this station
	public void addPassengerRequest(int destination, int request)
	{
		passengerRequests[destination] += request;
	}
	//deloads the amount of passenger to a station
	public int loadingPassengers(int destination)
	{	//gets the amount
		int request = passengerRequests[destination];
		//change to 0 after being loaded
		passengerRequests[destination] = 0;
		return request;
	}
	
	
	//add the amount of people that arrvied from a certain station
	public void addArrivedPassengers(int firstStation , int numPassengers)
	{
		arrivedPassengers[firstStation] += numPassengers;
	}
	
	
	
	//sets the total Total DestionationRequest to a certain train stations
	public void addDestinationRequests(int destinationRequest, int numPassengers)
	{
		totalDestinationRequests[destinationRequest] += numPassengers;
	}
	
	
	//sets the trainApproaching to the trainNum if no train approaching
	public void trainApproaching(int trainNum)
	{
		approachingTrain = trainNum;
	}
	
	//checks if a train is already approaching
	public boolean trainApproachingCheck()
	{
		if(approachingTrain >= 0)
			return true;
		return false;
				
	}
	
	//prints the Station statistics
	public void printStationStatistics(int stationNum)
	{
		System.out.println("\nStation # "+ stationNum+ ": ");
		for(int i = 0; i < 5; i++)
		{
			System.out.println("Total Passenger Request to Station # "+i+": "+totalDestinationRequests[i]);
		}
		
	}
	
	
	
}
