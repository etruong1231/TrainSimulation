import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
//Eddie Truong 18063651
//Vatsal Rustagi 41346390
// Class that setup and controls the simulation
public class TrainSimulation
{	
	private static int totalSimTime;
	private static int simSecRate;
	ArrayList<PassengerArrival> PassengerList = new ArrayList<PassengerArrival>(); //arraylist of Passenger Arrivals
	String[] temp = new String[7]; // array of lines from file
	//Train manager that is shared by all thread with info about train stations
	TrainSystemManager SysManager = new TrainSystemManager(); 
	private Train trains[] = new Train[5];
	
	public void start() throws InterruptedException
	{
		new SimClock();
		SimClock.tick();
		getFileInfo();
		setInfo();
		//start threads
		for(int i = 0 ; i < 5 ; i++ )
		{
			Train train = new Train(i, SysManager);
			trains[i] = train;
			train.start();	
		}
		while(SimClock.getTime() < totalSimTime+1)
		{
			//checks if any passenger information
			for(PassengerArrival Passenger : PassengerList)
			{
			
				if ((SimClock.getTime()) % Passenger.getTimePeriod() == 0)
				{	
					//sets up the train station to add values 
					SysManager.setUp(Passenger.getIntialStation(), 
						Passenger.getnumPassengers(), Passenger.getDestinationTrain(),
						Passenger.getTimePeriod());
					//print when a passenger is requesting to go to a station
					System.out.println("\n["+SimClock.getTime()+"]: At Station "+Passenger.getIntialStation()+", " + Passenger.getnumPassengers()+ " passengers are requesting to go to station "+Passenger.getDestinationTrain() );
				}
			}
			
			// the simSecRate timer, will tick once after it reaches the time
			long timePassed = 0;
			long start = System.currentTimeMillis();
			while(timePassed < simSecRate)
			{
				timePassed = System.currentTimeMillis() - start ;
				//System.out.println(timePassed);
			}
				SimClock.tick();
		}
		
	}
	
	//prints statistics of the whole simulation
	public void printTrainState()
	{
		System.out.println("\nSimulation Statistics\n_____________________\n");
		System.out.println("Total Time Simulated: "+totalSimTime+"\n"
			+ "Simulated Second Rate: "+simSecRate+" Millisecond = 1 Simulated Second");
		System.out.println("**** Train Statistics ****");
		for(int i = 0; i < 5; i++)
		{
			trains[i].printStatistics();
		}
		System.out.println("\n**** Train Station Statistics ****");
		SysManager.printStatistics();
	}
	
	public void setInfo()
	{	//set up the Simulation total and rate
		totalSimTime = Integer.parseInt(temp[0]); 
		simSecRate = Integer.parseInt(temp[1]);
		//set up the Passenger arrivals
		for(int i = 2; i < 7; i++)
		{	
			//seperate string by ;
			String tempString = temp[i];
			String[] Sections =  tempString.split(";");
			for(int x= 0;x < Sections.length; x++ )
			{	//split the string in parts
				String[] Parts = Sections[x].split(" ");
				int startTrain = i-2;
				int numPass = Integer.parseInt(Parts[0]);
				int destinTrain = Integer.parseInt(Parts[1]);
				int timePeriod = Integer.parseInt(Parts[2]);
				int expectedTime = Math.abs(startTrain-destinTrain-1) * 5 + 20;

				//makes the passengerarivial object and add them to the arraylist
				PassengerArrival Pass = new PassengerArrival(startTrain, numPass, destinTrain,
					timePeriod, expectedTime) ;
				PassengerList.add(Pass);
			}
			
			
			
		}
		
	}
	
	
	public void getFileInfo()
	{
		int counter = 0;
		try {
			File myInFile = new File("src/TrainConfig.txt"); //Constructs an instance of a file object for myFile.txt
			Scanner s = new Scanner(myInFile); //Contructs a scanner object to create an input stream from myInFile
			s.useDelimiter("\n"); // Sets the delimiter for the s scanner instance to a newline
			String tempString = ""; // declares and initializes an empty string variable
			
			while (s.hasNext()){ // checks my input file for remaining lines
				tempString = s.next(); //pulls the "token" or stream of data up to the delimiter from the file into tempString
				//System.out.println(tempString);
				//set the strings into the array of strings
				temp[counter++] = tempString;	
			}
			s.close(); //closes the scanner stream
							
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		
	}
	public static int getSimSecRate()
	{
		return simSecRate;
	}
	public static int getTotalTime()
	{
		return totalSimTime;
	}
	
	
}
