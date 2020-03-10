package day4.securecontainer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Enrique Alonso
 */
public class SecureContainerSimpleAdjacencyTests {
	private SecureContainer secureContainer;

	@Before
	public void init(){
		secureContainer = new SecureContainerSimpleAdjacency();
	}
	@Test
	public void testAdjacency(){
		assertTrue(secureContainer.checkAdjacency(111111));
	}

	@Test
	public void testAdjacency2(){
		assertFalse(secureContainer.checkAdjacency(123789));
	}

	@Test
	public void testIncrease(){
		assertFalse(secureContainer.checkIncrease(223450));
	}
}
