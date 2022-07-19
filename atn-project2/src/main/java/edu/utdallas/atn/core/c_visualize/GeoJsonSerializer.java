package edu.utdallas.atn.core.c_visualize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.*;

public class GeoJsonSerializer {

  public static void main(String[] args) throws JsonProcessingException {
    FeatureCollection featureCollection = new FeatureCollection();

    Feature point1 = new Feature();
    point1.setGeometry(new Point(101.2471, 37.2368));

    Feature line1 = new Feature();
    line1.setGeometry(new LineString(new LngLatAlt(32.123,24.587), new LngLatAlt(36.1478,29.3645)));

    featureCollection.add(point1);
    featureCollection.add(line1);

    String json = new ObjectMapper().writeValueAsString(featureCollection);
    System.out.println(json);
  }
}
