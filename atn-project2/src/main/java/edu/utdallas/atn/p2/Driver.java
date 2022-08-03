package edu.utdallas.atn.p2;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.utdallas.atn.p2.core.a_param_gen.ParameterGenerator;
import edu.utdallas.atn.p2.core.b_algos.HeuristicsAlgo1;
import edu.utdallas.atn.p2.core.b_algos.HeuristicsAlgo2;
import edu.utdallas.atn.p2.core.c_visualize.GeoJsonSerializer;
import edu.utdallas.atn.p2.domain.Graph;
import edu.utdallas.atn.p2.domain.Point;

import java.util.List;
import java.util.Scanner;

public class Driver {
  public static void main(String[] args) throws JsonProcessingException {
    // 1. Input Generation
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter N");
    int n = sc.nextInt();

    ParameterGenerator pg = new ParameterGenerator(n);
    List<Point> coordinates = pg.generateCoordinates();

    // 2. Execution

    // 2.1 Calculating the cost of complete graph
    Graph result0 = new Graph(coordinates);
    result0.makeItCompleteGraph();

    // 2.2 Calculating the cost of Heuristic 1 & Heuristic 2 algorithms
    Graph result1 = new HeuristicsAlgo1(coordinates).solve();
    Graph result2 = new HeuristicsAlgo2(coordinates).solve();

    // 3. Visualization
    String geoJson0 = new GeoJsonSerializer(result0).toJson();
    System.out.println(geoJson0);
    System.out.println("-----------");

    String geoJson1 = new GeoJsonSerializer(result1).toJson();
    System.out.println(geoJson1);
    System.out.println("-----------");

    String geoJson2 = new GeoJsonSerializer(result2).toJson();
    System.out.println(geoJson2);

    System.out.println("Complete Graph Cost 0: " + Math.round(result0.getCost()));
    System.out.println("Heuristic Cost 1: " + Math.round(result1.getCost()));
    System.out.println("Heuristic Cost 2: " + Math.round(result2.getCost()));
  }
}
