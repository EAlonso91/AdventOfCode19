package day1.fuelcalculator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

/**
 * @author Enrique Alonso
 */
public class FuelCalculatorTests2 {
	@Test
	public void calculateFuelBasedOnMass1(){
		int fuel = FuelCalculator.calculateFuelBasedOnMass(12, true);
		assertThat(fuel, is(2));
	}
	@Test
	public void calculateFuelBasedOnMass2(){
		int fuel = FuelCalculator.calculateFuelBasedOnMass(14, true);
		assertThat(fuel, is(2));
	}
	@Test
	public void calculateFuelBasedOnMass3(){
		int fuel = FuelCalculator.calculateFuelBasedOnMass(1969, true);
		assertThat(fuel, is(966));
	}
	@Test
	public void calculateFuelBasedOnMass4(){
		int fuel = FuelCalculator.calculateFuelBasedOnMass(100756, true);
		assertThat(fuel, is(50346));
	}
	@Test
	public void calculateTotalSpacecraftFuel(){
		int fuel = FuelCalculator.calculateTotalSpacecraftFuel(new int[]{12, 14, 1969, 100756}, true);
		assertThat(fuel, is(51316));
	}
}
