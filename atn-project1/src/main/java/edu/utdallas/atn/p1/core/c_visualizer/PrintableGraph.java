package edu.utdallas.atn.p1.core.c_visualizer;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;

import java.io.File;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class PrintableGraph {

  MutableGraph printableGraph;
  String imageName;

  public PrintableGraph(int k) {
    printableGraph = mutGraph("Optimal Network").setDirected(true);
    imageName = "graph_" + k + ".png";
  }

  public void addEdge(int start, int end) {
    printableGraph.add(mutNode("" + start).addLink(mutNode("" + end)));
  }

  public int getEdgeCount() {
    return printableGraph.edges().size();
  }

  public void render() {

    try {
      Graphviz.fromGraph(printableGraph)
          .width(800)
          .render(Format.PNG)
          .toFile(new File("output/" + imageName));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
