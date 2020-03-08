import day1.fuelcalculator.FuelCalculator;
import day2.programalarm.CalculateNounAndVerb;
import day2.programalarm.ProgramAlarm;
import day3.crossedwires.CrossPathsDetector;

/**
 * @author Enrique Alonso
 */
public class Main {
	public static void main(String[] args) {
		//******************DAY 1******************
		int finalFuel = FuelCalculator.calculateFuelFromSpecsFile("Day1/Day1Input1.txt", false);
		System.out.println("Day 1 part 1 solution: "+finalFuel);
		int finalFuel2 = FuelCalculator.calculateFuelFromSpecsFile("Day1/Day1Input1.txt", true);
		System.out.println("Day 1 part 2 solution: "+finalFuel2);

		//******************DAY 2******************
		String outputSequence = ProgramAlarm.runIntcodeFromFile("Day2/Day2Input1.txt", 12, 2);
		System.out.println("Day 2 part 1 solution: "+outputSequence.split(",")[0]);
		System.out.println("Day 2 part 2 solution: "+ CalculateNounAndVerb.calculatePairParameters());

		//******************DAY 3******************
		long start = System.currentTimeMillis();
		int shortestDistance = CrossPathsDetector.calculateShortestCrossDistanceFromFile("Day3/Day3Input1.txt");
		System.out.println("Day 3 part 1 solution: "+shortestDistance);
		long finish = System.currentTimeMillis();
		long timeElapsed = (finish - start)/1000;
		System.out.println("time: "+timeElapsed+"s");
	}
}
