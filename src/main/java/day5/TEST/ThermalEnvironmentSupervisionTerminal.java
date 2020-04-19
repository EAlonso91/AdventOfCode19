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
public class ThermalEnvironmentSupervisionTerminal {
	private static final Logger LOGGER = LoggerFactory.getLogger(ThermalEnvironmentSupervisionTerminal.class);

	protected static void runIntcode(final String intcodeSequence, int systemID) {
		int[] intcodeArray = init(intcodeSequence, systemID);
		int nextJump = 0;
		for (int i = 2; i < intcodeArray.length; i += nextJump) {
			int[] instructionCode = Converters.stringToDigitsArray(String.valueOf(intcodeArray[i]));
			int opcode = extractOpcodeFromInstruction(instructionCode);
			int[] parameterModes = extactParameterModes(instructionCode, opcode);
			if (opcode == 99) {
				break;
			} else if (opcode == 4) {
				LOGGER.info("" + intcodeArray[intcodeArray[i + 1]]);
				nextJump = 2;
			} else {
				int value1 = parameterModes[0] == 0 ? intcodeArray[intcodeArray[i + 1]] : intcodeArray[i + 1];
				int value2 = parameterModes[1] == 0 ? intcodeArray[intcodeArray[i + 2]] : intcodeArray[i + 2];
				if (opcode == 5 || opcode == 6) {
					JumpOpcode jumpOp = new JumpOpcode(instructionCode, opcode);
					jumpOp.setParameters(value1, value2);
					JumpResult result = jumpOp.jump(i);
					i = result.getCursor();
					nextJump = result.getJump();
				} else {
					WriteOpcode writeOp = new WriteOpcode(instructionCode, opcode);
					writeOp.setParameters(value1, value2);
					intcodeArray[intcodeArray[i + 3]] = writeOp.write();
					nextJump = writeOp.jump();
					//LOGGER.info("full instruction="+arrayCopy[i]+"/opcode="+opcode+"/parameterModes[0]="+parameterModes[0]+"/parameterModes[1]="+parameterModes[1]+"/1st value="+arrayCopy[i + 1]+"/2nd value="+arrayCopy[i + 2]);
				}
			}
		}
	}

	private static int[] init(final String intcodeSequence, final int systemID) {
		int[] intcodeArray = Converters.delimitedStringToDigitsArray(intcodeSequence, ",");

		if (intcodeArray[0] != 3) {
			throw new RuntimeException("Invalid start sequence");
		}
		intcodeArray[intcodeArray[1]] = systemID;
		return intcodeArray;
	}

	private static int extractOpcodeFromInstruction(final int[] instructionCode) {
		if (instructionCode.length == 1) {
			return instructionCode[0];
		} else {
			return Integer.parseInt(
					"" + instructionCode[instructionCode.length - 2] + instructionCode[instructionCode.length - 1]);
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