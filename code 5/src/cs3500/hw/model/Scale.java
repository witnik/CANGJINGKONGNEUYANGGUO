package model;

import java.util.ArrayList;

/**
 * This is the Scale class that implements IAnimation interface. This class represent the animation
 * of scaling a shape.
 */
public class Scale extends AbstractAnimation {

  private float width;
  private float height;

  /**
   * The constructor of the Scale class.
   *
   * @param s      the Shape this Scale animation will take place on
   * @param width  the width of the shape
   * @param height the height of the shape
   * @param start  when does the Scale animation start
   * @param end    when does the Scale animation end
   */
  Scale(Shape s, float width, float height, int start, int end) {
    super(s, start, end);
    this.width = width;
    this.height = height;
    this.start = start;
  }

  public float getNwidth() {
    return this.width;
  }

  public float getNheight() {
    return this.height;
  }
  /**
   * This method proceed the Scale animation on the Shape object.
   */
  @Override
  public void act() {
    s.scale(this.width, this.height, this.start, this.end);
  }

  /**
   * This method outputs what the Scale animation does to an object.
   *
   * @return a String about what the Scale animation does
   */
  public String toString() {
    return "Shape " + s.getName() + " scales from Width: " + s.getWidth() + ", Height: " +
            s.getHeight() + " to Width: " + this.width + ", Height: " + this.height +
            " from t=" + this.start + " to t=" + this.end;
  }
}
