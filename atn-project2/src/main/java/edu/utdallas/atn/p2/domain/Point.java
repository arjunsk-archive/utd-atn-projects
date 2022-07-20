package edu.utdallas.atn.p2.domain;

import org.apache.lucene.util.SloppyMath;

import java.util.Objects;

public class Point {
  private final double lat;
  private final double lng;

  public Point(double lat, double lng) {
    this.lat = lat;
    this.lng = lng;
  }

  public double getLat() {
    return lat;
  }

  public double getLng() {
    return lng;
  }

  public double distanceTo(Point end) {
    return SloppyMath.haversinMeters(this.getLat(), this.getLng(), end.getLat(), end.getLng());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Point)) return false;
    Point point = (Point) o;
    return Double.compare(point.getLat(), getLat()) == 0
        && Double.compare(point.getLng(), getLng()) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLat(), getLng());
  }
}
