package day3.crossedwires;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utils.ResourcePathExtractor;

public class CrossPathsDetector {
	private static final Point centralPort = new Point(0, 0);

	public static int calculateShortestCrossDistance(String pathWire1, String pathWire2) {
		String[] directionsWire1 = pathWire1.split(",");
		String[] directionsWire2 = pathWire2.split(",");

		List<Point> visitedPointsWire1 = new ArrayList<>();
		List<Point> visitedPointsWire2 = new ArrayList<>();

		calculateVisitedPoints(directionsWire1, visitedPointsWire1);
		calculateVisitedPoints(directionsWire2, visitedPointsWire2);

		int minDistance = 0;
		try {
			minDistance = visitedPointsWire1.stream()
					.filter(visitedPointsWire2::contains)
					.mapToInt(p -> p.calculateManhattanDistance(centralPort))
					.min()
					.getAsInt();
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Wires aren't crossed at any point!");
		}
		return minDistance;
	}

	private static void calculateVisitedPoints(String[] directions, List<Point> visitedPoints) {
		Point currentLocation = new Point(0, 0);
		for (String move : directions) {
			int distance = Integer.parseInt(move.substring(1));
			if (move.startsWith("D")) {
				for (int i = 1; i <= distance; i++) {
					visitedPoints.add(new Point(currentLocation.getxAxis(),currentLocation.getyAxis()-i));
				}
				currentLocation.moveDown(distance);
			}
			else if (move.startsWith("U")) {
				for (int i = 1; i <= distance; i++) {
					visitedPoints.add(new Point(currentLocation.getxAxis(),currentLocation.getyAxis()+i));
				}
				currentLocation.moveUp(distance);
			} else if (move.startsWith("L")) {
				for (int i = 1; i <= distance; i++) {
					visitedPoints.add(new Point(currentLocation.getxAxis()-i,currentLocation.getyAxis()));
				}
				currentLocation.moveLeft(distance);
			} else if (move.startsWith("R")) {
				for (int i = 1; i <= distance; i++) {
					visitedPoints.add(new Point(currentLocation.getxAxis()+i,currentLocation.getyAxis()));
				}
				currentLocation.moveRight(distance);
			} else {
				throw new RuntimeException("Invalid move instruction");
			}
		}
	}


	public static int calculateShortestCrossDistanceFromFile(String filePath) {
		int closestDistance = 0;
		try (Stream<String> lines = Files.lines(ResourcePathExtractor.openResource(filePath))) {
			List<String> pathsList = lines.collect(Collectors.toList());
			if (pathsList.size() != 2) {
				throw new RuntimeException("The tool is not prepared yet to unravel more than 2 wires!");
			}
			closestDistance = calculateShortestCrossDistance(pathsList.get(0), pathsList.get(1));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return closestDistance;
	}
}
