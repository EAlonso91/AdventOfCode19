package day8.spaceimageformat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import org.tc33.jheatchart.HeatChart;

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

		long nOnesInMinLayer = Arrays.stream(layersMap.get(layerWithMinZeroes))
				.filter(c -> c == 1)
				.count();
		long nTwosInMinLayer = Arrays.stream(layersMap.get(layerWithMinZeroes))
				.filter(c -> c == 2)
				.count();

		return nOnesInMinLayer * nTwosInMinLayer;
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
				.get()
				.getKey();
	}

	private static Map<Integer, int[]> initLayersMap(final int wide, final int tall, final int[] digits) {
		if (digits.length % (wide * tall) != 0) {
			throw new RuntimeException("Invalid parameters");
		}
		Map<Integer, int[]> layersMap = new HashMap<>();
		int layerSize = wide * tall;
		int layers = digits.length / layerSize;
		for (int i = 1; i < layers; i++) {
			int startIndex = (i - 1) * layerSize;
			int endIndex = layerSize * i;
			layersMap.put(i, Arrays.copyOfRange(digits, startIndex, endIndex));
			//System.out.println(Arrays.toString(Arrays.copyOfRange(digits, startIndex, endIndex)));
		}
		return layersMap;
	}

	public static long findResultInLayerWithLessZeroesFromFile(int wide, int tall, String filePath) {
		long result = 0;
		try {
			Path path = ResourcePathExtractor.openResource(filePath);
			String sequence = new String(Files.readAllBytes(path));
			result = findResultInLayerWithLessZeroes(wide, tall, sequence);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void printImageFromFile(int wide, int tall, String filePath) {
		try {
			Path path = ResourcePathExtractor.openResource(filePath);
			String sequence = new String(Files.readAllBytes(path));
			printImage(wide, tall, sequence);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printImage(int wide, int tall, String sequence) {
		int[] digits = Converters.stringToDigitsArray(sequence);
		Map<Integer, int[]> layersMap = initLayersMap(wide, tall, digits);
		print2D(generateImage(wide, tall, layersMap), wide, tall);
	}

	public static void print2D(int mat[][], int wide, int tall) {
		for (int[] x : mat) {
			for (int y : x) {
				System.out.print(y + " ");
			}
			System.out.println();
		}
		//saveToHeatChartFile(mat, wide, tall);
	}

	private static void saveToHeatChartFile(final int[][] mat, final int wide, final int tall) {
		double[][] doubleMatrix = new double[tall][wide];
		for (int i = 0; i < tall; i++) {
			doubleMatrix[i] = Arrays.stream(mat[i])
					.map(p -> p == 0 ? 1 : 0)//only black should be black (1), transparent and white are white (1)
					.asDoubleStream()
					.toArray();
		}
		HeatChart map = new HeatChart(doubleMatrix);
		try {
			map.saveToFile(new File("java-heat-chart.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int[][] generateImage(final int wide, final int tall, final Map<Integer, int[]> layersMap) {
		int[][] image = new int[tall][wide];
		int layerSize = wide * tall;
		int rowCounter = 0;
		int columnCounter = 0;
		for (int i = 0; i < layerSize; i++) {
			if (columnCounter == wide) {
				rowCounter++;
				columnCounter = 0;
			}
			int pixelValue = 2;
			for (int[] layer : layersMap.values()) {
				pixelValue = layer[i];
				if (pixelValue != 2) {
					break;
				}
			}
			image[rowCounter][columnCounter] = pixelValue;
			columnCounter++;
		}
		return image;
	}
}
