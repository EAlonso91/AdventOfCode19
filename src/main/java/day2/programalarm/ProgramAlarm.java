package day2.programalarm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import utils.ResourcePathExtractor;

/**
 * @author Enrique Alonso
 */
public class ProgramAlarm {
	public static String runIntcode(final String intcodeSequence) {
		return "";
	}

	private static boolean opcodeChecker(String input) {
		return input.equals("1") || input.equals("2") || input.equals("99");
	}

	public static String runIntcodeFromFile(String filePath) {
		String content = "";
		try {
			Path path = ResourcePathExtractor.openResource("Day2/Day2Input1.txt");
			content = new String(Files.readAllBytes(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
}
