package edu.utdallas.atn.core.c_visualize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.utdallas.atn.utils.Pair;
import org.geojson.*;

import java.util.List;

public class GeoJsonSerializer {

  private final List<Pair<Double, Double>> points;
  private final List<Pair<Pair<Double, Double>, Pair<Double, Double>>> edges;

  public static void main(String[] args) throws JsonProcessingException {
    FeatureCollection featureCollection = new FeatureCollection();

    Feature point1 = new Feature();
    point1.setGeometry(new Point(101.2471, 37.2368));

    Feature line1 = new Feature();
    line1.setGeometry(
        new LineString(new LngLatAlt(32.123, 24.587), new LngLatAlt(36.1478, 29.3645)));

    featureCollection.add(point1);
    featureCollection.add(line1);

    String json = new ObjectMapper().writeValueAsString(featureCollection);
    System.out.println(json);
  }

  public GeoJsonSerializer(
      List<Pair<Double, Double>> points,
      List<Pair<Pair<Double, Double>, Pair<Double, Double>>> edges) {
    this.points = points;
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
