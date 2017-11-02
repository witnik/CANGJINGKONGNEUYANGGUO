package model;

import java.util.ArrayList;

/**
 * This is the Move class that implements IAnimation interface. This class represent the animation
 * of moving the shape from one place to another.
 */
public class Move extends AbstractAnimation {
  private float x1;
  private float y1;

  /**
   * This is the constructor of the Move class.
   *
   * @param s     the Shape object this move will take place on
   * @param x1    x value of the posn this move will move to
   * @param y1    y value of the posn this move will move to
   * @param start when does this move start
   * @param end   when does this move end
   */
  public Move(Shape s, float x1, float y1, int start, int end) {
    super(s, start, end);
    this.x1 = x1;
    this.y1 = y1;
    change.add((x1 - s.getX())/(float)(end - start));
    change.add((y1 - s.getX())/(float)(end - start));
  }

  /**
   * This method proceed the Move animation on the Shape object.
   */
  @Override
  public void act() {
    s.move(x1, y1, start, end);
  }

  /**
   * This method get the change per unit time of this IAnimation
   * @return change per unit time of this IAnimation
   */
  public ArrayList<Float> getChange() {
    return change;
  }

  /**
   * This method outputs what the Move animation does to an object.
   *
   * @return a String about what the Move animation does
   */
  public String toString() {
    return "Shape " + s.getName() + " moves from (" + s.getX() + "," + s.getY() + ") to ("
            + this.x1 + "," + this.y1 + ") from time t=" + this.start + " to t=" + this.end;
  }
}
