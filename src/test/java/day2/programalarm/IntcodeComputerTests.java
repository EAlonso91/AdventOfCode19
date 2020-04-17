package day2.programalarm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Enrique Alonso
 */
public class IntcodeComputerTests {
	@Test
	public void testRunIntcode1(){
		String output = IntcodeComputer.runIntcode("1,0,0,0,99");
		assertEquals("2,0,0,0,99", output);
	}

	@Test
	public void testRunIntcode2(){
		String output = IntcodeComputer.runIntcode("2,3,0,3,99");
		assertEquals("2,3,0,6,99", output);
	}

	@Test
	public void testRunIntcode3(){
		String output = IntcodeComputer.runIntcode("2,4,4,5,99,0");
		assertEquals("2,4,4,5,99,9801", output);
	}

	@Test
	public void testRunIntcode4(){
		String output = IntcodeComputer.runIntcode("1,1,1,4,99,5,6,0,99");
		assertEquals("30,1,1,4,2,5,6,0,99", output);
	}
}
