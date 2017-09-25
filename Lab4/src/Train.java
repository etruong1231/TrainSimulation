import java.util.ArrayList;
//Vatsal Rustagi 41346390
//Eddie Truon 18063651
//Class representing a train and its behavior
public class Train implements Runnable
{
	private Thread t;
	private String threadName;
	
	private int trainID;
	private int numPassengers;
	private int totalLoadedPassengers;
	private int totalUnloadedPassengers;
	ArrayList<TrainEvent> moveQueue = new ArrayList<TrainEvent>();
	private int [] passengerDestinations = new int[5];
	private int currentStation = 0;
	TrainSystemManager manager;
	
	
	
	public Train(int TrainID, TrainSystemManager newManager)
	{
		threadName = "Train # " + String.valueOf(TrainID);
		manager = newManager;
		this.trainID = TrainID;
		
	}
	
	public void run()
	{
		while(true){
			//train is not doing anything will look for passenger at train station
			//checks if any station is open 
			if(moveQueue.size() == 0)
			{
				
				//check if theres a station that needs pickup
				int stationWaiting = manager.checkForWaitingStation(trainID);
				//found a station and set that this train is going to it
				if(stationWaiting != -1)
				{
					
					//need to create an event to go trainstation that needs to pick up people
					TrainEvent event = new TrainEvent(stationWaiting, Math.abs(stationWaiting-currentStation)*5 - 5);
					moveQueue.add(event);	
	
				}
			}
			//if it has a movequeue itll do the event
			if(moveQueue.size() > 0)
			{	
				//goes to the station that needs to be picked up
				int destination = moveQueue.get(0).getDestination();
				System.out.println("\n["+SimClock.getTime()+"]: " +threadName +" is heading to Station # "+destination+ " for pickup");
				int travelingtime = moveQueue.get(0).getExpectedArrivalTime();
				//removes the first moveQueue to get ready for next
				moveQueue.remove(0);
				//check if its at the same station for pickup for time
				if(currentStation == destination)
				{
					travelingtime+= 5;
				}
				int finish = SimClock.getTime() + 10 + travelingtime;
				//loading the passenger at the station
				//the wait time
				while(finishTime(finish) == false){}
				//change currentstation
				currentStation = destination;
				//check if theres people going up first
				int newDestination = manager.getPassengerDestinationUp(currentStation);
					if (newDestination != -1)
					{
						TrainEvent event2 = new TrainEvent(newDestination, Math.abs(currentStation-newDestination)*5 - 5);
						moveQueue.add(event2);
					}
					// if no one going up, then they are going down
					else
					{
						newDestination = manager.getPassengerDestinationDown(currentStation);
						TrainEvent event2 = new TrainEvent(newDestination, Math.abs(currentStation-newDestination)*5 - 5);
						moveQueue.add(event2);
					}
				//now need to move to the destination dropoff
				destination = moveQueue.get(0).getDestination();
				//get the amount of passenger 
				passengerDestinations[destination] = manager.getPassengerAmount(currentStation,destination);
				//current passenger amount
				numPassengers = passengerDestinations[destination];
				//increment  total passenger amount
				totalLoadedPassengers += numPassengers;
				//print the amount they loaded
				print_loading(currentStation);
				//set station as no incoming
				manager.setNoIncomingTrain(currentStation);
				//set the passenger destination back to 0
				passengerDestinations[destination] = 0;
				//now need to move to the destination dropoff
				System.out.println("\n["+SimClock.getTime()+"]: " +threadName +" is heading to Station # "+destination+ " for drop off");
			    //time it has to get there and drop off
				travelingtime = moveQueue.get(0).getExpectedArrivalTime();
			    finish = SimClock.getTime() + 10 + travelingtime;
			    //it is traveling then unloads
			    while(finishTime(finish) == false){}
				System.out.println("\n"+"["+SimClock.getTime()+"]: "+ threadName+" unloaded "+ numPassengers + " passengers at Station # "+ destination);
				
				totalUnloadedPassengers += numPassengers;
				currentStation = destination;
				moveQueue.remove(0);
			}
		
		}
		
		
	}
	public void print_loading(int destination)
	{
		System.out.println();
		for(int i = 0; i < 5; i++)
		{
			if(passengerDestinations[i] > 0)
			{
				int passAmount = passengerDestinations[i];
				System.out.println("["+SimClock.getTime()+"]: "+ threadName+" is loaded with "+ passAmount + " passengers at Station # "+ destination+ " going to Station # " +i);
	
			}
		}
	}
	public synchronized boolean finishTime(int finishTime)
	{
		return SimClock.getTime() == finishTime;
	}
	
	public void printStatistics()
	{
		System.out.println("\n"+threadName+": Total Passengers Loaded: "+ totalLoadedPassengers
			+"\n           Total Passengers Unloaded: "+ totalUnloadedPassengers);
	
				
	}
	public void start()
	{
		System.out.println("Starting " + threadName);
		if (t == null)
		{
			t = new Thread(this, threadName);
			t.start();
		}
	}
	

}
