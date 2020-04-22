package day8.spaceimageformat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import utils.Converters;
import utils.ResourcePathExtractor;

/**
 * @author Enrique Alonso
 */
public class ImageDecoder {

	public static long findResultInLayerWithLessZeroes(int wide, int tall, String sequence) {
		int[] digits = Converters.stringToDigitsArray(sequence);
		Map<Integer, int[]> layersMap = initLayersMap(wide, tall, digits);
		Integer layerWithMinZeroes = findLayerWithLessZeroes(layersMap);

		long nOnesInMinLayer = Arrays.stream(layersMap.get(layerWithMinZeroes)).filter(c -> c == 1).count();
		long nTwosInMinLayer = Arrays.stream(layersMap.get(layerWithMinZeroes)).filter(c -> c == 2).count();

		return nOnesInMinLayer*nTwosInMinLayer;
	}

	private static Integer findLayerWithLessZeroes(final Map<Integer, int[]> layersMap) {
		Map<Integer, Long> zeroesCountsMap = layersMap.entrySet()
				.stream()
				.collect(Collectors.toMap(e -> e.getKey(), e -> Arrays.stream(e.getValue())
						.filter(c -> c == 0)
						.count()));
		return zeroesCountsMap.entrySet()
				.stream()
				.min((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
				.get().getKey();
	}

	private static Map<Integer, int[]> initLayersMap(final int wide, final int tall, final int[] digits) {
		if (digits.length % (wide * tall) != 0) {
			throw new RuntimeException("Invalid parameters");
		}
		Map<Integer, int[]> layersMap = new HashMap<>();
		int layerSize = wide * tall;
		int layers = digits.length / layerSize;
		for (int i = 1; i < layers; i++) {
			int startIndex = (i-1)*layerSize;
			int endIndex = layerSize * i;
			layersMap.put(i, Arrays.copyOfRange(digits, startIndex, endIndex));
		}
		return layersMap;
	}

	public static long findResultInLayerWithLessZeroesFromFile(int wide, int tall, String filePath) {
		long result=0;
		try {
			Path path = ResourcePathExtractor.openResource(filePath);
			String sequence = new String(Files.readAllBytes(path));
			result=findResultInLayerWithLessZeroes(wide, tall, sequence);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return result;

	}
}
