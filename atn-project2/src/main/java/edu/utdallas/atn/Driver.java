package edu.utdallas.atn;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.utdallas.atn.core.a_param_gen.ParameterGenerator;
import edu.utdallas.atn.core.b_algos.HeuristicsAlgo1;
import edu.utdallas.atn.core.b_algos.HeuristicsAlgo2;
import edu.utdallas.atn.core.c_visualize.GeoJsonSerializer;
import edu.utdallas.atn.utils.Pair;
import edu.utdallas.atn.utils.Topology;

import java.util.List;
import java.util.Scanner;

public class Driver {
  public static void main(String[] args) throws JsonProcessingException {
    // 1. Input Generation
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter N");
    int n = sc.nextInt();

    ParameterGenerator pg = new ParameterGenerator(n);
    List<Pair<Double, Double>> coordinates = pg.generateCoordinates();

    // 2. Algo execution
    Topology result1 = new HeuristicsAlgo1(coordinates).solve();
    Topology result2 = new HeuristicsAlgo2(coordinates).solve();

    // 3. Visualization
    String geoJson1 = new GeoJsonSerializer(result1).toJson();
    System.out.println(geoJson1);

    String geoJson2 = new GeoJsonSerializer(result2).toJson();
    System.out.println(geoJson2);
  }
}