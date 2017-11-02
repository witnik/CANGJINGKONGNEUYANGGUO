package model;

import java.util.ArrayList;

/**
 * This is an abstract class that implements the IAnimation interface.
 */
public abstract class AbstractAnimation implements IAnimation {
  Shape s;
  int start;
  int end;
  ArrayList<Float> change;

  AbstractAnimation(Shape s, int start, int end) {
    this.s = s;
    this.start = start;
    this.end = end;
    change = new ArrayList<Float>();
  }

  /**
   * This method is a getter that gets the start time of the Scale animation.
   *
   * @return the start time of the Scale animation
   */

  public int getStart() {
    return this.start;
  }

  /**
   * This method is a getter that gets the end time of the Scale animation.
   *
   * @return the end time of the Scale animation
   */

  public int getEnd() {
    return this.end;
  }

  /**
   * This method get the shape of this object
   * @return the shape of this object
   */
  public Shape getShape() {
    return this.s;
  }
}
