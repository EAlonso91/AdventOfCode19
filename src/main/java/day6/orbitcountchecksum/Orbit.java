package day6.orbitcountchecksum;

/**
 * @author Enrique Alonso
 */
public class Orbit {
	private String source;
	private String target;

	public Orbit(final String source, final String target) {
		this.source = source;
		this.target = target;
	}

	public String getSource() {
		return source;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(final String target) {
		this.target = target;
	}
}
