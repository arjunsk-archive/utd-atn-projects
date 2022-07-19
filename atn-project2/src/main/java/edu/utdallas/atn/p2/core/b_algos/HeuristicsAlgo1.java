package edu.utdallas.atn.p2.core.b_algos;

import edu.utdallas.atn.p2.domain.Edge;
import edu.utdallas.atn.p2.domain.Graph;
import edu.utdallas.atn.p2.domain.Point;

import java.util.Map;
import java.util.PriorityQueue;

public class HeuristicsAlgo1 {

  private Graph graph;
  private final PriorityQueue<Edge> edgesPq;

  public HeuristicsAlgo1(Map<Point, Integer> coordinates) {
    this.graph = new Graph(coordinates);
    this.edgesPq = new PriorityQueue<>((a, b) -> Double.compare(b.getDistance(), a.getDistance()));
  }

  public Graph solve() {
    // 1. make it a complete graph
    this.graph.makeItCompleteGraph();

    // 2. Push all the edges to the PQ
    this.edgesPq.addAll(this.graph.getEdges());

    int numberOfEdgeRemovals = 0;
    while (!edgesPq.isEmpty()) {
      numberOfEdgeRemovals++;
      // Copy original graph
      Graph oldGraph = new Graph(this.graph);

      Edge largestDistanceEdge = edgesPq.poll();
      assert largestDistanceEdge != null;
      this.graph.removeEdge(largestDistanceEdge);

      if (this.graph.getDiameter() > 4 || this.graph.getSmallestDegree() < 3) {
        this.graph = oldGraph;
        break;
      }

      this.graph = oldGraph;
    }

    System.out.println(numberOfEdgeRemovals);
    return this.graph;
  }
}
