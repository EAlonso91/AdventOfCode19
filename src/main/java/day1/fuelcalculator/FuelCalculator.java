package day1.fuelcalculator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Enrique Alonso
 */
public class FuelCalculator {
	public static int calculateFuelBasedOnMass(final int mass, boolean considerFuelMass) {
		if(considerFuelMass){
			int currentMass = (mass / 3) - 2;
			int accumulator = 0;
			while(currentMass>0){
				accumulator += currentMass;
				currentMass = (currentMass / 3) - 2;
			}
			return accumulator;
		}
		else {
			return (mass / 3) - 2;
		}
	}


	public static int calculateTotalSpacecraftFuel(final int[] moduleMasses, boolean considerFuelMass) {
		return Arrays.stream(moduleMasses)
				.map(mass -> calculateFuelBasedOnMass(mass, considerFuelMass))
				.sum();
	}

	public static int calculateFuelFromSpecsFile(String filePath, boolean considerFuelMass) {
		int totalFuel = 0;
		File file = new File(FuelCalculator.class.getClassLoader()
				.getResource("Day1/Day1Input1.txt")
				.getFile());
		try (Stream<String> lines = Files.lines(file.toPath())) {
			int[] moduleMasses = lines.mapToInt(Integer::parseInt)
					.toArray();
			totalFuel = calculateTotalSpacecraftFuel(moduleMasses, considerFuelMass);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return totalFuel;
	}
}
