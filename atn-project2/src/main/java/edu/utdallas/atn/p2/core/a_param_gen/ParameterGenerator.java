package edu.utdallas.atn.p2.core.a_param_gen;

import edu.utdallas.atn.p2.domain.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ParameterGenerator {

  private static final double MIN_LAT = 32.995995;
  private static final double MAX_LAT = 33.041047;

  private static final double MIN_LNG = -456.732388;
  private static final double MAX_LNG = -456.650677;

  private final int nodeCount;

  public ParameterGenerator(int nodeCount) {
    this.nodeCount = nodeCount;
  }

  public Map<Point, Integer> generateCoordinates() {

    Map<Point, Integer> coordinates = new HashMap<>();

    for (int i = 0; i < nodeCount; i++) {
      double lat = getRandomCoordinate(MIN_LAT, MAX_LAT);
      double lng = getRandomCoordinate(MIN_LNG, MAX_LNG);
      coordinates.put(new Point(lat, lng), i);
    }

    return coordinates;
  }

  private double getRandomCoordinate(double min, double max) {
    return ThreadLocalRandom.current().nextDouble(min, max);
  }
}
