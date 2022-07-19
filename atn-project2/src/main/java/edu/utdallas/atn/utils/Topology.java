package edu.utdallas.atn.utils;

import java.util.List;

public class Topology {

  List<Point> coordinates;
  List<Edge> edges;

  public Topology(List<Point> coordinates, List<Edge> edges) {
    this.coordinates = coordinates;
    this.edges = edges;
  }

  public List<Point> getCoordinates() {
    return coordinates;
  }

  public List<Edge> getEdges() {
    return edges;
  }
}
