package day5.TEST;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import day2.programalarm.IntcodeComputer;
import utils.Converters;
import utils.ResourcePathExtractor;

/**
 * @author Enrique Alonso
 */
public class ThermalEnvironmentSupervisionTerminal extends IntcodeComputer {
	private static final Logger LOGGER = LoggerFactory.getLogger(ThermalEnvironmentSupervisionTerminal.class);

	static {
		ACCEPTED_OPCODES = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 99 };
	}

	protected static void runIntcode(final String intcodeSequence, int systemID) {
		int[] intcodeArray = Converters.delimitedStringToDigitsArray(intcodeSequence, ",");
		int[] arrayCopy = intcodeArray;

		if (arrayCopy[0] != 3) {
			throw new RuntimeException("Invalid start sequence");
		}
		arrayCopy[arrayCopy[1]] = systemID;

		int nextJump = 0;
		for (int i = 2; i < arrayCopy.length; i += nextJump) {
			int[] instructionCode = Converters.stringToDigitsArray(String.valueOf(arrayCopy[i]));
			int opcode = extractOpcode(instructionCode);
			if (!isOpcode(opcode)) {
				throw new RuntimeException("Invalid sequence");
			}
			int[] parameterModes = extactParameterModes(instructionCode, opcode);
			if (opcode == 99) {
				break;
			} else if (opcode == 4) {
				LOGGER.info("" + arrayCopy[arrayCopy[i + 1]]);
				nextJump = 2;
			} else {
				int value1 = parameterModes[0] == 0 ? arrayCopy[arrayCopy[i + 1]] : arrayCopy[i + 1];
				int value2 = parameterModes[1] == 0 ? arrayCopy[arrayCopy[i + 2]] : arrayCopy[i + 2];
				if (opcode == 5) {
					if (value1 != 0) {
						i = value2;
						nextJump = 0;
					} else
						nextJump = 3;
				} else if (opcode == 6) {
					if (value1 == 0) {
						i = value2;
						nextJump = 0;
					} else
						nextJump = 3;
				} else {
					if (opcode == 1) {
						//LOGGER.info("full instruction="+arrayCopy[i]+"/opcode="+opcode+"/parameterModes[0]="+parameterModes[0]+"/parameterModes[1]="+parameterModes[1]+"/1st value="+arrayCopy[i + 1]+"/2nd value="+arrayCopy[i + 2]);
						arrayCopy[arrayCopy[i + 3]] = value1 + value2;
					} else if (opcode == 2) {
						arrayCopy[arrayCopy[i + 3]] = value1 * value2;
					} else if (opcode == 7) {
						if (value1 < value2) {
							arrayCopy[arrayCopy[i + 3]] = 1;
						} else
							arrayCopy[arrayCopy[i + 3]] = 0;
					} else if (opcode == 8) {
						if (value1 == value2) {
							arrayCopy[arrayCopy[i + 3]] = 1;
						} else
							arrayCopy[arrayCopy[i + 3]] = 0;
					}
					nextJump = 4;
				}
			}
		}
	}

	private static int[] extactParameterModes(final int[] instructionCode, int opcode) {
		if (instructionCode.length > 5) {
			throw new RuntimeException("Invalid parameters");
		}
		if (opcode != 3 || opcode != 4 || opcode != 99) {
			if (instructionCode.length < 3) {
				return new int[] { 0, 0 };
			} else if (instructionCode.length == 3) {
				return new int[] { instructionCode[0], 0 };
			} else if (instructionCode.length == 4) {
				return new int[] { instructionCode[1], instructionCode[0] };
			} else if (instructionCode.length == 5) {
				return new int[] { instructionCode[2], instructionCode[1] };
			}
		}
		return null;//no parameter modes needed
	}

	private static int extractOpcode(final int[] instructionCode) {
		if (instructionCode.length == 1) {
			return instructionCode[0];
		} else {
			return Integer.parseInt(
					"" + instructionCode[instructionCode.length - 2] + instructionCode[instructionCode.length - 1]);
		}
	}

	public static void runIntcodeFromFile(String filePath, int systemID) {
		try {
			Path path = ResourcePathExtractor.openResource(filePath);
			String intcode = new String(Files.readAllBytes(path));
			runIntcode(intcode, systemID);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}