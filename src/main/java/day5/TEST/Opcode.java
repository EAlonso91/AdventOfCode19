package day5.TEST;

import java.util.Arrays;

/**
 * @author Enrique Alonso
 */
public abstract class Opcode {
	 int[] parameters;
	 int opcode;
	 static final int[] ACCEPTED_OPCODES = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 99 };


	public Opcode (int[] instructionCode, int opcode){
		this.opcode = opcode;
		if (!isOpcode(opcode)) {
			throw new RuntimeException("Invalid sequence");
		}
		parameters = new int[2];
	}
	public int getOpcode() {
		return opcode;
	}

	private static boolean isOpcode(int input) {
		return Arrays.stream(ACCEPTED_OPCODES).anyMatch(n -> n==input);
	}


	void setParameters(int... values) {
		int index = 0;
		for (int v : values)
			parameters[index++] = v;
	}

	int[] getParameters() {
		return parameters;
	}
}
