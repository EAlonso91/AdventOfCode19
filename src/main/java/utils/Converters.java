package utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Converters {
	public static int[] stringToDigitsArray(String s){
		return s.chars().map(c -> c - '0').toArray();
	}

	public static int[] delimitedStringToDigitsArray(String s, String delimiter){
		return Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();
	}

	public static String digitsArrayToString(int[] digitsArray, String delimiter){
		return Arrays.stream(digitsArray).mapToObj(String::valueOf).collect(Collectors.joining(delimiter));
	}
}
