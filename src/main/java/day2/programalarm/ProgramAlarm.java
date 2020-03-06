package day2.programalarm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import utils.Converters;
import utils.ResourcePathExtractor;

/**
 * @author Enrique Alonso
 */
public class ProgramAlarm {
	public static String runIntcode(final String intcodeSequence) {
		int[] intcodeArray = Converters.delimitedStringToDigitsArray(intcodeSequence,",");
		int[] arrayCopy = intcodeArray;
		for (int i = 0; i < arrayCopy.length; i+=4) {
			int opcode = arrayCopy[i];
			if(!isOpcode(opcode)){
				throw new RuntimeException("Invalid sequence");
			}
			else if (opcode == 1){
				arrayCopy[arrayCopy[i+3]] = arrayCopy[arrayCopy[i+1]] + arrayCopy[arrayCopy[i+2]];
			}
			else if (opcode == 2){
				arrayCopy[arrayCopy[i+3]] = arrayCopy[arrayCopy[i+1]] * arrayCopy[arrayCopy[i+2]];
			}
			else if (opcode == 99){
				break;
			}
		}
		return Converters.digitsArrayToString(arrayCopy,",");
	}

	private static boolean isOpcode(int input) {
		return input == 1 || input == 2 || input == 99;
	}

	public static String runIntcodeFromFile(String filePath, int noun, int verb) {
		String outputSequence = "";
		try {
			Path path = ResourcePathExtractor.openResource(filePath);
			String intcode = new String(Files.readAllBytes(path));
			outputSequence = runIntcode(applyTransformations(intcode, noun,verb));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputSequence;
	}

	 static String applyTransformations(String intcode, int noun, int verb) {
		String[] sequenceAsArray = intcode.split(",");
		sequenceAsArray[1]=String.valueOf(noun);
		sequenceAsArray[2]=String.valueOf(verb);
		return String.join(",", sequenceAsArray);
	}
}
