package edu.utdallas.atn.p2.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RectangleTest {

  Rectangle rectangle;

  @Before
  public void setUp() throws Exception {
    Point topRight = new Point(33.03101839279396, -456.9637334346771);
    Point bottomLeft = new Point(33.02803207049375, -456.9695377349853);

    this.rectangle = new Rectangle(bottomLeft, topRight);
  }

  @Test
  public void contains() {
    // 1. Arrange
    Point point = new Point(33.02852679955502, -456.96505308151245);

    // 2. Act
    boolean actual = this.rectangle.contains(point);

    // 3. Assert
    assertEquals(true, actual);
  }
}
