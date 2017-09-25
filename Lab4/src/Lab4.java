//contains the main main method that constructs a trainSimulation object and prints out some statistics
//Eddie Truong 18063651 Lab 4
//Vatsal Rustagi 41346390
public class Lab4
{

	public static void main(String [] args) throws InterruptedException
	{
		TrainSimulation TrainSim = new TrainSimulation();
		TrainSim.start();
		TrainSim.printTrainState();
		System.exit(0);
	}
}
