package day1.fuelcalculator;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
/**
 * @author Enrique Alonso
 */
public class FuelCalculatorTests1 {
	@Test
	public void calculateFuelBasedOnMass1(){
		int fuel = FuelCalculator.calculateFuelBasedOnMass(12, false);
		assertThat(fuel, is(2));
	}
	@Test
	public void calculateFuelBasedOnMass2(){
		int fuel = FuelCalculator.calculateFuelBasedOnMass(14, false);
		assertThat(fuel, is(2));
	}
	@Test
	public void calculateFuelBasedOnMass3(){
		int fuel = FuelCalculator.calculateFuelBasedOnMass(1969, false);
		assertThat(fuel, is(654));
	}
	@Test
	public void calculateFuelBasedOnMass4(){
		int fuel = FuelCalculator.calculateFuelBasedOnMass(100756, false);
		assertThat(fuel, is(33583));
	}
	@Test
	public void calculateTotalSpacecraftFuel(){
		int fuel = FuelCalculator.calculateTotalSpacecraftFuel(new int[]{12, 14, 1969, 100756}, false);
		assertThat(fuel, is(34241));
	}
}
