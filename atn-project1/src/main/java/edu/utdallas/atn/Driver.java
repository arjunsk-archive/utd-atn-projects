package edu.utdallas.atn;

import edu.utdallas.atn.core.a_param_gen.ParameterGenerator;
import edu.utdallas.atn.core.b_algo.NetworkCostAlgorithm;
import edu.utdallas.atn.core.b_algo.Output;

import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {

    // 1. Input Generation
    Scanner sc = new Scanner(System.in);

    int n = 21; // may be based on 2021!
    System.out.println("Enter K");
    int k = sc.nextInt();

    ParameterGenerator pg = new ParameterGenerator(n);
    pg.setDynamicArgument(k);

    int[][] a = pg.generateUnitCosts();
    int[][] b = pg.generateTrafficDemands();

    // 2. Init the Algorithm
    NetworkCostAlgorithm algo = new NetworkCostAlgorithm(n, k, a, b);
    Output ans = algo.calculate();
    System.out.println("Total cost for the network: " + ans.getCost());
    System.out.println("Density for the network: " + ans.getDensity());

    // 3. Visualize
    ans.getGraph().render();
  }
}
