package edu.utdallas.atn.p2.core.b_algos;

import edu.utdallas.atn.p2.core.c_visualize.GeoJsonSerializer;
import edu.utdallas.atn.p2.domain.Edge;
import edu.utdallas.atn.p2.domain.Graph;
import edu.utdallas.atn.p2.domain.Point;
import edu.utdallas.atn.p2.domain.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeuristicsAlgo2 {
  private final List<Point> coordinates;

  public HeuristicsAlgo2(List<Point> coordinates) {
    this.coordinates = coordinates;
  }

  public Graph solve() {
    // 1. Create segments
    Rectangle[][] rectangles = generateSegments();

    // 2. Add Points to segments
    bucketPointsToSegments(rectangles);

    // 3. Convert Rectangle points to individual graphs
    Graph[][] segmentGraphs = generateSegmentGraphs(rectangles);

    // 4. Strongly connect individual segmentGraphs
    makeIndividualSegmentGraphsCompleteGraph(segmentGraphs);

    // 5. Add edge from each segment to the center segment
    Graph output = new Graph(coordinates);
    stronglyConnectAllSegments(segmentGraphs, output);

    // 6. Merge rest of the segments to the main graph
    mergeSegmentsToMainGraph(segmentGraphs, output);

    return output;
  }

  private void mergeSegmentsToMainGraph(Graph[][] segmentGraphs, Graph output) {
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        for (Edge edge : segmentGraphs[r][c].getEdges()) {
          output.addEdge(edge);
        }
      }
    }
  }

  private void stronglyConnectAllSegments(Graph[][] segmentGraphs, Graph output) {

    List<Point> segmentCenters = new ArrayList<>();
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        if (segmentGraphs[r][c].getCenter().isPresent())
          segmentCenters.add(segmentGraphs[r][c].getCenter().get());
      }
    }

    for (int i = 0; i < segmentCenters.size(); i++) {
      for (int j = 0; j < segmentCenters.size(); j++) {
        if (i == j) continue;
        output.addEdge(new Edge(segmentCenters.get(i), segmentCenters.get(j)));
      }
    }
  }

  private void makeIndividualSegmentGraphsCompleteGraph(Graph[][] segmentGraphs) {
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        segmentGraphs[r][c].makeItCompleteGraph();
      }
    }
  }

  private void bucketPointsToSegments(Rectangle[][] rectangles) {
    for (Point point : coordinates) {

      boolean found = false;
      for (int r = 0; r < 3; r++) {
        if (found) break;

        for (int c = 0; c < 3; c++) {
          if (found) break;
          if (rectangles[r][c].contains(point)) {
            rectangles[r][c].add(point);
            found = true;
          }
        }
      }
    }
  }

  private Graph[][] generateSegmentGraphs(Rectangle[][] segments) {
    Graph[][] segmentGraphs = new Graph[3][3];
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        segmentGraphs[r][c] = segments[r][c].generateGraph();
      }
    }
    return segmentGraphs;
  }

  private Rectangle[][] generateSegments() {
    double maxLat = Integer.MIN_VALUE;
    double minLat = Integer.MAX_VALUE;

    double maxLng = Integer.MIN_VALUE;
    double minLng = Integer.MAX_VALUE;

    for (Point point : coordinates) {
      maxLat = Math.max(maxLat, point.getLat());
      minLat = Math.min(minLat, point.getLat());

      maxLng = Math.max(maxLng, point.getLng());
      minLng = Math.min(minLng, point.getLng());
    }

    // 1. Generate Boundary Rectangle
    Point outerRectangleTopLeft = new Point(minLat, maxLng);
    Point outerRectangleTopRight = new Point(maxLat, maxLng);

    Point outerRectangleBottomLeft = new Point(minLat, minLng);
    Point outerRectangleBottomRight = new Point(maxLat, minLng);

    // 2. Find deltaX & deltaY
    double width = outerRectangleBottomLeft.getLat() - outerRectangleBottomRight.getLat();
    double height = outerRectangleBottomLeft.getLng() - outerRectangleTopLeft.getLng();

    double deltaX = width / 3.0;
    double deltaY = height / 3.0;

    // 3. Create segment corners
    Point[][] points = new Point[4][4];
    List<Point> segmentBoundaryPoints = new ArrayList<>();
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {

        double lat = outerRectangleTopRight.getLat() + r * deltaX;
        double lng = outerRectangleTopRight.getLng() + c * deltaY;
        points[r][c] = new Point(lat, lng);
        segmentBoundaryPoints.add(points[r][c]);
      }
    }

    // 4. Create segments
    Rectangle[][] segments = new Rectangle[3][3];
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        Point bottomLeft = points[r + 1][c + 1];
        Point topRight = points[r][c];
        segments[r][c] = new Rectangle(bottomLeft, topRight);
      }
    }

    /* Test Part */
    System.out.println("Segments");

    Graph _temp = new Graph(segmentBoundaryPoints);
    _temp.makeItCompleteGraph();

    Map<String, Object> properties = new HashMap<>();
    properties.put("marker-color", "#bf1818");

    System.out.println(new GeoJsonSerializer(_temp, properties).toJson());

    return segments;
  }
}
