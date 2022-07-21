package edu.utdallas.atn.p2.core.b_algos;

import edu.utdallas.atn.p2.domain.Edge;
import edu.utdallas.atn.p2.domain.Graph;
import edu.utdallas.atn.p2.domain.Point;

import java.util.List;
import java.util.PriorityQueue;

public class HeuristicsAlgo1 {

  private Graph graph;
  private final PriorityQueue<Edge> edgesPq;

  public HeuristicsAlgo1(List<Point> coordinates) {
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

      Edge largestDistanceEdge = edgesPq.poll();
      Graph newGraph = new Graph(this.graph);
      newGraph.removeEdge(largestDistanceEdge);

      if (!newGraph.getDiameter().isPresent()
          || newGraph.getDiameter().get() > 4
          || newGraph.getSmallestDegree() < 3) break;

      numberOfEdgeRemovals++;
      this.graph = newGraph;
    }

    //    System.out.println(numberOfEdgeRemovals);
    return this.graph;
  }
}
