package edu.utdallas.atn.p2.core.a_param_gen;

import edu.utdallas.atn.p2.domain.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ParameterGenerator {

  private static final double LAT_MIN = -456.732388;
  private static final double LNG_MIN = 32.995995;

  private static final double LAT_MAX = -456.650677;
  private static final double LNG_MAX = 33.041047;

  private final int nodeCount;

  public ParameterGenerator(int nodeCount) {
    this.nodeCount = nodeCount;
  }

  public Map<Point, Integer> generateCoordinates() {

    Map<Point, Integer> coordinates = new HashMap<>();

    for (int i = 0; i < nodeCount; i++) {
      double x = getRandomCoordinate(LAT_MIN, LAT_MAX);
      double y = getRandomCoordinate(LNG_MIN, LNG_MAX);
      coordinates.put(new Point(x, y), i);
    }

    return coordinates;
  }

  private double getRandomCoordinate(double min, double max) {
    return ThreadLocalRandom.current().nextDouble(min, max);
  }
}
