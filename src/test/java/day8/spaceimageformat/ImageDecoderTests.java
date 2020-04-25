package day8.spaceimageformat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

	@Test
	public void generateImageTest(){

		Map<Integer, int[]> layersMap = new HashMap<>();
		layersMap.put(1,new int[]{0,2,2,2});
		layersMap.put(2,new int[]{1,1,2,2});
		layersMap.put(3,new int[]{2,2,1,2});
		layersMap.put(4,new int[]{0,0,0,0});
		int[][] resultMatrix = ImageDecoder.generateImage(2,2,layersMap);
		ImageDecoder.print2D(resultMatrix,2,2);
	}
}
