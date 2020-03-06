import day1.fuelcalculator.FuelCalculator;

/**
 * @author Enrique Alonso
 */
public class Main {
	public static void main(String[] args) {
		int finalFuel = FuelCalculator.calculateFuelFromSpecsFile("Day1Input1.txt", false);
		System.out.println("Day 1 part 1 solution: "+finalFuel);
		int finalFuel2 = FuelCalculator.calculateFuelFromSpecsFile("Day1Input1.txt", true);
		System.out.println("Day 1 part 2 solution: "+finalFuel2);
	}
}
