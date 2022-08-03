package edu.utdallas.atn.p2.core.a_param_gen;

import edu.utdallas.atn.p2.domain.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ParameterGenerator {

  private static final double MIN_LAT = 32.984100;
  private static final double MAX_LAT = 32.995995;

  private static final double MIN_LNG = -456.732388;
  private static final double MAX_LNG = -456.650677;

  private final int nodeCount;

  public ParameterGenerator(int nodeCount) {
    this.nodeCount = nodeCount;
  }

  public List<Point> generateCoordinates() {

    List<Point> coordinates = new ArrayList<>();

    for (int i = 0; i < nodeCount; i++) {
      double lat = getRandomCoordinate(MIN_LAT, MAX_LAT);
      double lng = getRandomCoordinate(MIN_LNG, MAX_LNG);
      coordinates.add(new Point(lat, lng));
    }

    return coordinates;
  }

  private double getRandomCoordinate(double min, double max) {
    return ThreadLocalRandom.current().nextDouble(min, max);
  }
}
