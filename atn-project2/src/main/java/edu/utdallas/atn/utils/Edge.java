package edu.utdallas.atn.utils;

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
}
