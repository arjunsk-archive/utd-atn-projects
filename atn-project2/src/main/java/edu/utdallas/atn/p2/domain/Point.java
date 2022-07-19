package edu.utdallas.atn.p2.domain;

import org.apache.lucene.util.SloppyMath;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Point)) return false;
    Point point = (Point) o;
    return getLat().equals(point.getLat()) && getLng().equals(point.getLng());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLat(), getLng());
  }
}
