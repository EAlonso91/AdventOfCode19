package day6.orbitcountchecksum;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

/**
 * @author Enrique Alonso
 */
public class OrbitCountTest {

	@Test
	public void calculateOrbit() {
		List<Orbit> orbits = OrbitCount.init(new String[] { "COM)B", "B)C", "C)D", "D)E}" });
		assertEquals(3, OrbitCount.orbitsToOrigin(new Orbit("C", "D"), orbits, "COM").getDistance());
	}

	@Test
	public void calculateOrbit2() {
		List<Orbit> orbits = OrbitCount.init(new String[] { "COM)B", "B)C", "C)D", "D)E", "E)J", "J)K", "K)L" });
		assertEquals(7, OrbitCount.orbitsToOrigin(new Orbit("K", "L"), orbits, "COM").getDistance());
	}

	@Test
	public void calculateOrbit3() {
		assertEquals(42, OrbitCount.calculateTotalOrbits(
				new String[] { "COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L" }));
	}

	@Test
	public void calculateDistance() {
		assertEquals(4, OrbitCount.distanceBetweenObjects("YOU", "SAN", new String[] { "COM)B",
																					   "B)C",
																					   "C)D",
																					   "D)E",
																					   "E)F",
																					   "B)G",
																					   "G)H",
																					   "D)I",
																					   "E)J",
																					   "J)K",
																					   "K)L",
																					   "K)YOU",
																					   "I)SAN" }));
	}
}