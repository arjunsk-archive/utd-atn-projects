package edu.utdallas.atn.p2.domain;

public class Edge {
  Point start;
  Point end;

  public Edge(Point start, Point end) {
    this.start = start;
    this.end = end;
  }

  public Point getStart() {
    return start;
  }

  public Point getEnd() {
    return end;
  }

  public Double getDistance() {
    return start.distanceTo(end);
  }
}
