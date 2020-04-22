package day6.orbitcountchecksum;

import java.util.List;

/**
 * @author Enrique Alonso
 */
public class OrbitPath {
	private List<String> path;
	private int distance;

	public OrbitPath(final List<String> path, final int distance) {
		this.path = path;
		this.distance = distance;
	}

	public List<String> getPath() {
		return path;
	}

	public void setPath(final List<String> path) {
		this.path = path;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(final int distance) {
		this.distance = distance;
	}

}
