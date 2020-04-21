package day6.orbitcountchecksum;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.ResourcePathExtractor;

/**
 * @author Enrique Alonso
 */
public class OrbitCount {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrbitCount.class);

	public static int calculateOrbitCountChecksumFromFile(String filePath) {
		int totalOrbits = 0;
		try (Stream<String> lines = Files.lines(ResourcePathExtractor.openResource(filePath))) {
			String[] orbitsArray = lines.toArray(String[]::new);
			totalOrbits = calculateTotalOrbits(orbitsArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return totalOrbits;
	}

	public static int calculateDistanceFromFile(String source, String target, String filePath) {
		int distance = 0;
		try (Stream<String> lines = Files.lines(ResourcePathExtractor.openResource(filePath))) {
			String[] orbitsArray = lines.toArray(String[]::new);
			distance = distanceBetweenObjects(source, target, orbitsArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return distance;
	}

	public static int calculateTotalOrbits(final String[] orbitsArray) {
		List<Orbit> orbitPairs = init(orbitsArray);
		String origin = setOrigin(orbitPairs);
		int orbits = 0;
		for (Orbit orbit : orbitPairs) {
			int newOrbits = orbitsToOrigin(orbit, orbitPairs, origin, new ArrayList<>());
			orbits += newOrbits;
		}
		return orbits;
	}

	public static List<Orbit> init(final String[] orbitsArray) {
		List<Orbit> orbitPairs = new ArrayList<>();
		for (String orbit : orbitsArray) {
			addOrbit(orbitPairs, orbit);
		}
		return orbitPairs;
	}

	private static String setOrigin(final List<Orbit> orbitPairs) {
		List<String> targets = orbitPairs.stream()
				.map(Orbit::getTarget)
				.collect(Collectors.toList());
		String origin = orbitPairs.stream()
				.map(Orbit::getSource)
				.filter(s -> !targets.contains(s))
				.findFirst()
				.get();
		if (origin == null) {
			throw new RuntimeException("No origin!");
		}
		return origin;
	}

	public static int orbitsToOrigin(final Orbit orbit, final List<Orbit> orbitPairs, String origin,
			List<String> path) {
		int orbits = 1;
		String planet = orbit.getSource();
		path.add(planet);
		while (true) {
			path.add(planet);
			if (planet.equals(origin)) {
				break;
			}
			final String finalPlanet = planet;
			orbits++;
			planet = getOrbitFromTarget(finalPlanet, orbitPairs).getSource();
		}
		return orbits;
	}

	public static int distanceBetweenObjects(String sourceObject, String targetObject, final String[] orbitsArray) {
		List<Orbit> orbitPairs = init(orbitsArray);
		String origin = setOrigin(orbitPairs);
		Orbit initialOrbitFromSource = getOrbitFromTarget(sourceObject, orbitPairs);
		Orbit initialOrbitFromTarget = getOrbitFromTarget(targetObject, orbitPairs);
		List<String> pathFromSourceToOrigin = new ArrayList<>();
		int distanceFromSourceToOrigin = orbitsToOrigin(initialOrbitFromSource, orbitPairs, origin,
				pathFromSourceToOrigin);
		List<String> pathFromTargetToOrigin = new ArrayList<>();
		int distanceFromTargetToOrigin = orbitsToOrigin(initialOrbitFromTarget, orbitPairs, origin,
				pathFromTargetToOrigin);
		try {
			String commonObject = pathFromSourceToOrigin.stream()
					.filter(p -> pathFromTargetToOrigin.contains(p))
					.findFirst()
					.get();
			LOGGER.info("Intersection planet: "+commonObject);
			System.out.println(commonObject);
			int distanceFromCommonPointToOrigin = orbitsToOrigin(getOrbitFromTarget(commonObject, orbitPairs),
					orbitPairs, origin, new ArrayList<>());
			return (distanceFromSourceToOrigin - distanceFromCommonPointToOrigin) + (distanceFromTargetToOrigin
					- distanceFromCommonPointToOrigin) - 2;
		} catch (NoSuchElementException e) {
			throw new RuntimeException("No path between source and target objects!");
		}
	}

	private static Orbit getOrbitFromTarget(final String targetObject, final List<Orbit> orbitPairs) {
		return orbitPairs.stream()
				.filter(o -> o.getTarget()
						.equals(targetObject))
				.findFirst()
				.get();
	}

	private static void addOrbit(final List<Orbit> orbitPairs, final String orbit) {
		String[] orbitSplitted = orbit.split("\\)");
		if (orbitSplitted.length != 2) {
			throw new RuntimeException("Invalid orbit value");
		}
		orbitPairs.add(new Orbit(orbitSplitted[0], orbitSplitted[1]));
	}
}
