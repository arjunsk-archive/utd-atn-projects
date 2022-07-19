package edu.utdallas.atn.p2.domain;

import org.apache.lucene.util.SloppyMath;

public class Point {
  private final Double lat;
  private final Double lng;

  public Point(Double lat, Double lng) {
    this.lat = lat;
    this.lng = lng;
  }

  public Double getLat() {
    return lat;
  }

  public Double getLng() {
    return lng;
  }

  public Double distanceTo(Point end) {
    return SloppyMath.haversinMeters(this.getLat(), this.getLng(), end.getLat(), end.getLng());
  }
}
