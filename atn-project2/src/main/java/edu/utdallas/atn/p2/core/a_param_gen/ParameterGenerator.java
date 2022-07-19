package edu.utdallas.atn.p2.core.a_param_gen;

import edu.utdallas.atn.p2.utils.Point;

import java.util.ArrayList;
import java.util.List;
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

  public List<Point> generateCoordinates() {

    List<Point> coordinates = new ArrayList<>();

    for (int i = 0; i < nodeCount; i++) {
      double x = getRandomCoordinate(LAT_MIN, LAT_MAX);
      double y = getRandomCoordinate(LNG_MIN, LNG_MAX);
      coordinates.add(new Point(x, y));
    }

    return coordinates;
  }

  private double getRandomCoordinate(double min, double max) {
    return ThreadLocalRandom.current().nextDouble(min, max);
  }
}
