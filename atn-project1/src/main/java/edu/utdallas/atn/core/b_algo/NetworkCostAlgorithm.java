package edu.utdallas.atn.core.b_algo;

import edu.utdallas.atn.core.c_visualizer.PrintableGraph;
import edu.utdallas.atn.utils.Pair;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.ArrayList;
import java.util.List;

public class NetworkCostAlgorithm {

  private final int n;
  private final int k;

  private final int[][] a;
  private final int[][] b;

  private final SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge> graph;

  public NetworkCostAlgorithm(int n, int k, int[][] a, int[][] b) {
    this.graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
    this.n = n;
    this.k = k;

    this.a = a;
    this.b = b;

    // add vertices
    for (int i = 0; i < n; i++) graph.addVertex(i);

    // add edges
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {

        if (i == j) continue;

        DefaultWeightedEdge edge = graph.addEdge(i, j);
        double weight = a[i][j];
        graph.setEdgeWeight(edge, weight);
      }
    }
  }

  private int findShortestPathCost(int start, int end) {
    GraphPath<Integer, DefaultWeightedEdge> path =
        DijkstraShortestPath.findPathBetween(graph, start, end);

    int cost = 0;
    for (DefaultWeightedEdge edge : path.getEdgeList()) cost += graph.getEdgeWeight(edge);

    return cost;
  }

  private List<Pair<Integer, Integer>> findShortestPathEdges(int start, int end) {
    GraphPath<Integer, DefaultWeightedEdge> path =
        DijkstraShortestPath.findPathBetween(graph, start, end);

    List<Pair<Integer, Integer>> result = new ArrayList<>();
    for (int i = 0; i < path.getVertexList().size() - 1; i++) {
      result.add(new Pair<>(path.getVertexList().get(i), path.getVertexList().get(i + 1)));
    }

    return result;
  }

  public Output calculate() {

    PrintableGraph printableGraph = new PrintableGraph(k);
    int totalCost = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {

        // calc cost
        int currCost = b[i][j] * findShortestPathCost(i, j);
        totalCost += currCost;

        // calc graph
        for (Pair<Integer, Integer> edge : findShortestPathEdges(i, j)) {
          printableGraph.addEdge(edge.getKey(), edge.getValue());
        }
      }
    }

    // calc density
    double totalPossibleNumberOfDirectedEdges = n * (n - 1);
    double numberOfDirectedEdgesWithNonZeroWeights = printableGraph.getEdgeCount();
    double density = numberOfDirectedEdgesWithNonZeroWeights / totalPossibleNumberOfDirectedEdges;

    return new Output(printableGraph, totalCost, density);
  }
}
