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

  /**
   * This is the constructor for the abstract class AbstractAnimation
   *
   * @param s     the Shape object this animation is acting on
   * @param start the starting time of this animation
   * @param end   the ending time of this animation
   */
  AbstractAnimation(Shape s, int start, int end) {
    this.s = s;
    this.start = start;
    this.end = end;
    change = new ArrayList<Float>();
    change.add(null);
    change.add(null);
    change.add(null);
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
   *
   * @return the shape of this object
   */
  public Shape getShape() {
    return this.s;
  }
}
