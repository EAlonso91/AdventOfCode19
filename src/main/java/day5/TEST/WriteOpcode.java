package day5.TEST;

import java.io.Serializable;

/**
 * @author Enrique Alonso
 */
public class WriteOpcode extends Opcode {
	public WriteOpcode(int[] instructionCode, int opcode) {
		super(instructionCode, opcode);
	}

	public int write() {
		if (opcode == 1)
			return op1();
		else if (opcode == 2)
			return op2();
		else if (opcode == 7)
			return op7();
		else if (opcode == 7)
			return op7();
		else if (opcode == 8)
			return op8();
		else
			throw new RuntimeException("Invalid opcode");
	}

	private int op1() {
		return parameters[0] + parameters[1];
	}

	private int op2() {
		return parameters[0] * parameters[1];
	}

	private int op7() {
		if (parameters[0] < parameters[1]) {
			return 1;
		} else
			return 0;
	}

	private int op8() {
		if (parameters[0] == parameters[1]) {
			return 1;
		} else
			return 0;
	}

	public int jump() {
		return 4;
	}
}
