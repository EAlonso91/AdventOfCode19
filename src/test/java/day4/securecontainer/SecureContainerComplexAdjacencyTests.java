package day4.securecontainer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Enrique Alonso
 */
public class SecureContainerComplexAdjacencyTests {
	private SecureContainer secureContainer;

	@Before
	public void init(){
		secureContainer = new SecureContainerComplexAdjacency();
	}

	@Test
	public void testAdjacency1(){
		assertTrue(secureContainer.checkAdjacency(112233));
	}

	@Test
	public void testAdjacency2(){
		assertFalse(secureContainer.checkAdjacency(123444));
	}

	@Test
	public void testAdjacency3(){
		assertTrue(secureContainer.checkAdjacency(111122));
	}
}
