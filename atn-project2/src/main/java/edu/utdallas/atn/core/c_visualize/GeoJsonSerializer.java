package edu.utdallas.atn.core.c_visualize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.utdallas.atn.utils.Edge;
import edu.utdallas.atn.utils.Point;
import edu.utdallas.atn.utils.Topology;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.LineString;
import org.geojson.LngLatAlt;

import java.util.ArrayList;
import java.util.List;

public class GeoJsonSerializer {

  private final List<Point> points;
  private final List<Edge> edges;

  public GeoJsonSerializer(List<Point> coordinates) {
    this(coordinates, new ArrayList<>());
  }

  public GeoJsonSerializer(Topology topology) {
    this(topology.getCoordinates(), topology.getEdges());
  }

  public GeoJsonSerializer(List<Point> coordinates, List<Edge> edges) {
    this.points = coordinates;
    this.edges = edges;
  }

  public String toJson() throws JsonProcessingException {
    FeatureCollection featureCollection = new FeatureCollection();

    for (Point point : this.points) {
      Feature _point = new Feature();
      _point.setGeometry(new org.geojson.Point(point.getX(), point.getY()));
      featureCollection.add(_point);
    }

    for (Edge edge : edges) {
      Feature _line = new Feature();
      Point start = edge.getStart();
      Point end = edge.getEnd();

      _line.setGeometry(
          new LineString(
              new LngLatAlt(start.getX(), start.getY()), new LngLatAlt(end.getX(), end.getY())));

      featureCollection.add(_line);
    }

    return new ObjectMapper().writeValueAsString(featureCollection);
  }
}
