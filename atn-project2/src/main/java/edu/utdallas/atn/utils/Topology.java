package edu.utdallas.atn.utils;

import java.util.List;

public class Topology {

  List<Pair<Double, Double>> coordinates;
  List<Pair<Pair<Double, Double>, Pair<Double, Double>>> edges;

  public Topology(
      List<Pair<Double, Double>> coordinates,
      List<Pair<Pair<Double, Double>, Pair<Double, Double>>> edges) {
    this.coordinates = coordinates;
    this.edges = edges;
  }

  public List<Pair<Double, Double>> getCoordinates() {
    return coordinates;
  }

  public List<Pair<Pair<Double, Double>, Pair<Double, Double>>> getEdges() {
    return edges;
  }
}
