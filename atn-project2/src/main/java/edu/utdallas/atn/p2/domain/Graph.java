package edu.utdallas.atn.p2.domain;

import java.util.*;

public class Graph {

  private final Map<Point, Integer> index;
  private final Map<Integer, Point> reverseIndex;

  private final int n;
  private final boolean[][] connectivityAdjMatrix;

  public Graph(Graph graph) {
    this.n = graph.n;

    // Deep Copy, Map
    this.index = new HashMap<>(graph.index);
    this.reverseIndex = new HashMap<>(graph.reverseIndex);

    // Deep Copy, 2D Array
    this.connectivityAdjMatrix = new boolean[n][n];
    for (int r = 0; r < n; r++)
      this.connectivityAdjMatrix[r] = Arrays.copyOf(graph.connectivityAdjMatrix[r], n);
  }

  public Graph(List<Point> points) {

    this.index = new HashMap<>();
    int counter = 0;
    for (Point point : points) this.index.put(point, counter++);

    this.reverseIndex = new HashMap<>();
    this.index.forEach((k, v) -> reverseIndex.put(v, k));

    this.n = index.size();
    this.connectivityAdjMatrix = new boolean[n][n];
  }

  /** Use {@link Graph#Graph(List)} ) } instead */
  @Deprecated
  public Graph(Map<Point, Integer> index) {
    this.index = index;
    this.reverseIndex = new HashMap<>();

    this.index.forEach((k, v) -> reverseIndex.put(v, k));
    this.n = index.size();
    this.connectivityAdjMatrix = new boolean[n][n];
  }

  public void addEdge(Edge edge) {
    Point start = edge.getStart();
    Point end = edge.getEnd();

    int startIndex = index.get(start);
    int endIndex = index.get(end);

    connectivityAdjMatrix[startIndex][endIndex] = true;
    connectivityAdjMatrix[endIndex][startIndex] = true;
  }

  public void removeEdge(Edge edge) {
    Point start = edge.getStart();
    Point end = edge.getEnd();

    int startIndex = index.get(start);
    int endIndex = index.get(end);

    connectivityAdjMatrix[startIndex][endIndex] = false;
    connectivityAdjMatrix[endIndex][startIndex] = false;
  }

  public void makeItCompleteGraph() {
    for (int r = 0; r < n; r++) {
      for (int c = 0; c < n; c++) {
        connectivityAdjMatrix[r][c] = true;
      }
    }
  }

  public int getSmallestDegree() {
    int minDegree = Integer.MAX_VALUE;
    for (int r = 0; r < n; r++) {
      int degree = 0;
      for (int c = 0; c < n; c++) {
        if (r == c) continue;
        if (connectivityAdjMatrix[r][c]) degree++;
      }
      minDegree = Math.min(degree, minDegree);
    }
    return minDegree;
  }

  public Optional<Integer> getDiameter() {

    // 1. create adjMatrix
    int[][] adjMatrix = new int[n][n];
    for (int r = 0; r < n; r++)
      for (int c = 0; c < n; c++) {
        if (r == c) adjMatrix[r][c] = 0;
        else if (connectivityAdjMatrix[r][c]) adjMatrix[r][c] = 1;
        else adjMatrix[r][c] = 1000;
      }

    // 2. run floyd-warshall to get the max hop-count from a vertex to every other vertex.
    for (int k = 0; k < n; k++) {
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < n; c++) {
          adjMatrix[r][c] = Math.min(adjMatrix[r][k] + adjMatrix[k][c], adjMatrix[r][c]);
        }
      }
    }

    // 3. find the largest hop distance.
    int largestHopDistance = 0;
    for (int r = 0; r < n; r++) {
      for (int c = 0; c < n; c++) {

        // Checks if all the nodes are connected in the graph.
        // If not, there is no point in getting the largest Hop Distance.
        if (adjMatrix[r][c] >= 1000) return Optional.empty();

        // finding the maximum
        largestHopDistance = Math.max(largestHopDistance, adjMatrix[r][c]);
      }
    }

    return Optional.of(largestHopDistance);
  }

  public List<Point> getCoordinates() {
    return new ArrayList<>(index.keySet());
  }

  public Optional<Point> getCenter() {
    return getCoordinates().size() == 0 ? Optional.empty() : Optional.of(getCoordinates().get(0));
  }

  public List<Edge> getEdges() {
    List<Edge> edges = new ArrayList<>();
    for (int r = 0; r < n; r++) {
      for (int c = r + 1; c < n; c++) {
        if (!connectivityAdjMatrix[r][c]) continue;

        Point start = reverseIndex.get(r);
        Point end = reverseIndex.get(c);

        edges.add(new Edge(start, end));
      }
    }

    return edges;
  }
}
