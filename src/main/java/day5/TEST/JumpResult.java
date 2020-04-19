package day5.TEST;

/**
 * @author Enrique Alonso
 */
public class JumpResult {
	private int cursor;
	private int jump;

	public JumpResult(final int cursor, final int jump) {
		this.cursor = cursor;
		this.jump = jump;
	}

	public int getCursor() {
		return cursor;
	}

	private void setCursor(final int cursor) {
		this.cursor = cursor;
	}

	public int getJump() {
		return jump;
	}

	private void setJump(final int jump) {
		this.jump = jump;
	}
}
