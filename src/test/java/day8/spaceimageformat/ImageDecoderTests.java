package day8.spaceimageformat;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Enrique Alonso
 */
public class ImageDecoderTests {
	@Test
	public void findResultInLayerWithLessZeroesTest(){
			Assert.assertEquals(1, ImageDecoder.findResultInLayerWithLessZeroes(3, 2, "123456789012"));
	}
}
