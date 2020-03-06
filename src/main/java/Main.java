import day1.fuelcalculator.FuelCalculator;
import day2.programalarm.CalculateNounAndVerb;
import day2.programalarm.ProgramAlarm;

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
	}
}
