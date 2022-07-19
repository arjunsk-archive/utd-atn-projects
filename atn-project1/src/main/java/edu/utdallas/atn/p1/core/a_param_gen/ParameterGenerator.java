package edu.utdallas.atn.p1.core.a_param_gen;

import edu.utdallas.atn.p1.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParameterGenerator {

  private final int[] userId;
  private final int nodeCount;
  int dynamicArgument;

  public ParameterGenerator(int nodeCount) {
    this.nodeCount = nodeCount;
    this.userId = CommonUtils.getUserId();
  }

  // b[i][j]
  public int[][] generateTrafficDemands() {
    int[][] b = new int[nodeCount][nodeCount];

    for (int i = 0; i < nodeCount; i++) {
      for (int j = 0; j < nodeCount; j++) {
        b[i][j] = Math.abs(userId[i] - userId[j]);
      }
    }

    return b;
  }

  // a[i][j]
  public int[][] generateUnitCosts() {

    int[][] a = new int[nodeCount][nodeCount];

    for (int i = 0; i < nodeCount; i++) {

      // pick random k indices
      List<Integer> list = new ArrayList<>();
      for (int currIdx = 0; currIdx < nodeCount; currIdx++) if (currIdx != i) list.add(currIdx);
      Collections.shuffle(list);
      list = list.subList(0, dynamicArgument);

      for (int j = 0; j < nodeCount; j++) {
        if (i == j) a[i][j] = 0; // self edge costs 0
        else if (list.contains(j)) a[i][j] = 1; // set k indices as 1
        else a[i][j] = 100; // rest as 100
      }
    }

    return a;
  }

  public void setDynamicArgument(int dynamicArgument) {
    this.dynamicArgument = dynamicArgument;
  }
}
