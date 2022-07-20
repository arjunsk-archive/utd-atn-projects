package edu.utdallas.atn.p2.core.c_visualize;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.utdallas.atn.p2.domain.Edge;
import edu.utdallas.atn.p2.domain.Graph;
import edu.utdallas.atn.p2.domain.Point;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.LineString;
import org.geojson.LngLatAlt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeoJsonSerializer {

  private final List<Point> points;
  private final List<Edge> edges;
  private final Map<String, Object> properties;

  public GeoJsonSerializer(List<Point> points, Map<String, Object> properties) {
    this(points, new ArrayList<>(), properties);
  }

  public GeoJsonSerializer(Graph graph) {
    this(graph.getCoordinates(), graph.getEdges(), new HashMap<>());
  }

  public GeoJsonSerializer(Graph graph, Map<String, Object> properties) {
    this(graph.getCoordinates(), graph.getEdges(), properties);
  }

  public GeoJsonSerializer(
      List<Point> coordinates, List<Edge> edges, Map<String, Object> properties) {
    this.points = coordinates;
    this.edges = edges;
    this.properties = properties;
  }

  public String toJson() {
    FeatureCollection featureCollection = new FeatureCollection();

    for (Point point : this.points) {
      Feature _point = new Feature();
      _point.setGeometry(new org.geojson.Point(point.getLng(), point.getLat()));
      _point.setProperties(properties);
      _point.setProperty("marker-size", "small");
      featureCollection.add(_point);
    }

    for (Edge edge : edges) {
      Feature _line = new Feature();
      Point start = edge.getStart();
      Point end = edge.getEnd();

      _line.setGeometry(
          new LineString(
              new LngLatAlt(start.getLng(), start.getLat()),
              new LngLatAlt(end.getLng(), end.getLat())));

      featureCollection.add(_line);
    }

    String json = "{}";
    try {
      json = new ObjectMapper().writeValueAsString(featureCollection);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return json;
  }
}
