package edu.utdallas.atn;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.utdallas.atn.core.a_param_gen.ParameterGenerator;
import edu.utdallas.atn.core.c_visualize.GeoJsonSerializer;
import edu.utdallas.atn.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
  public static void main(String[] args) throws JsonProcessingException {
    // 1. Input Generation
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter N");
    int n = sc.nextInt();

    ParameterGenerator pg = new ParameterGenerator(n);
    List<Pair<Double, Double>> points = pg.generateNodes();

    points.forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));

    String json = new GeoJsonSerializer(points, new ArrayList<>()).toJson();
    System.out.println(json);
  }
}
