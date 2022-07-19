package edu.utdallas.atn.core.c_visualize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.utdallas.atn.utils.Pair;
import edu.utdallas.atn.utils.Topology;
import org.geojson.*;

import java.util.ArrayList;
import java.util.List;

public class GeoJsonSerializer {

  private final List<Pair<Double, Double>> points;
  private final List<Pair<Pair<Double, Double>, Pair<Double, Double>>> edges;

  public GeoJsonSerializer(List<Pair<Double, Double>> coordinates) {
    this(coordinates, new ArrayList<>());
  }

  public GeoJsonSerializer(Topology topology) {
    this(topology.getCoordinates(), topology.getEdges());
  }

  public GeoJsonSerializer(
      List<Pair<Double, Double>> coordinates,
      List<Pair<Pair<Double, Double>, Pair<Double, Double>>> edges) {
    this.points = coordinates;
    this.edges = edges;
  }

  public String toJson() throws JsonProcessingException {
    FeatureCollection featureCollection = new FeatureCollection();

    for (Pair<Double, Double> point : this.points) {
      Feature _point = new Feature();
      _point.setGeometry(new Point(point.getKey(), point.getValue()));
      featureCollection.add(_point);
    }

    for (Pair<Pair<Double, Double>, Pair<Double, Double>> edge : edges) {
      Feature _line = new Feature();
      Pair<Double, Double> start = edge.getKey();
      Pair<Double, Double> end = edge.getKey();

      _line.setGeometry(
          new LineString(
              new LngLatAlt(start.getKey(), start.getValue()),
              new LngLatAlt(end.getKey(), end.getValue())));

      featureCollection.add(_line);
    }

    return new ObjectMapper().writeValueAsString(featureCollection);
  }
}
