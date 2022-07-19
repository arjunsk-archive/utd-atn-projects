package edu.utdallas.atn.core.b_algo;

import edu.utdallas.atn.core.c_visualizer.PrintableGraph;

public class Output {
  private final PrintableGraph graph;
  private final int cost;
  private final double density;

  public Output(PrintableGraph graph, int cost, double density) {
    this.graph = graph;
    this.cost = cost;
    this.density = density;
  }

  public PrintableGraph getGraph() {
    return graph;
  }

  public int getCost() {
    return cost;
  }

  public double getDensity() {
    return density;
  }
}
