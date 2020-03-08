package day3.crossedwires;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CrossPathsDetectorMinStepsTests {
	@Test
	public void calculateShortestCrossDistanceTest1() {
		assertThat(CrossPathsDetector.calculateMinSteps("R75,D30,R83,U83,L12,D49,R71,U7,L72",
				"U62,R66,U55,R34,D71,R55,D58,R83"), is(610));
	}
	@Test
	public void calculateShortestCrossDistanceTest2() {
		assertThat(CrossPathsDetector.calculateMinSteps("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51",
				"U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"), is(410));
	}
}
