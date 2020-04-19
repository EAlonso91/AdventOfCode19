package day5.TEST;

/**
 * @author Enrique Alonso
 */
public class JumpOpcode  extends Opcode {
	public JumpOpcode(int[] instructionCode, int opcode){
		super(instructionCode, opcode);
	}
	public JumpResult jump(int cursor){
		if(opcode==5){
			return op5(cursor);
		}
		else{
			return op6(cursor);
		}
	}

	private JumpResult op5(int cursor){
		if (parameters[0] != 0) {
			return new JumpResult( parameters[1],0);
		} else {
			return new JumpResult( cursor,3);
		}
	}
	private JumpResult op6(int cursor){
		if (parameters[0] == 0) {
			return new JumpResult( parameters[1],0);
		} else {
			return new JumpResult( cursor,3);
		}
	}
}
