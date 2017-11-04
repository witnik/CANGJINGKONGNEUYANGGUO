package model;

import java.util.ArrayList;

/**
 * This is the ChangeColor class that implements IAnimation interface. This class represent the
 * animation of color changing.
 */
public class ChangeColor extends AbstractAnimation {

  private float red;
  private float blue;
  private float green;

  /**
   * This is the contructor of the ChangeColor class.
   *
   * @param s     the Shape obejct this animation will take place on
   * @param red   the red data of the color this animation will change to
   * @param blue  the blue data of the color this animation will change to
   * @param green the green data of the color this animation will change to
   * @param start when does the animation start
   * @param end   when does the animation end
   */
  ChangeColor(Shape s, float red, float blue, float green, int start, int end) {
    super(s, start, end);
    this.red = red;
    this.blue = blue;
    this.green = green;
    change.set(0, (red - s.getRed()) / ((float) (end - start + 1)));
    change.set(1, (blue - s.getBlue()) / ((float) (end - start + 1)));
    change.set(2, (green - s.getGreen()) / ((float) (end - start + 1)));
  }

  /**
   * This method proceed the ChangeColor animation on the Shape object.
   */
  @Override
  public void act() {
    s.changeColor(red, blue, green, start, end);
  }

  /**
   * This method outputs what the ChangeColor animation does to an object.
   *
   * @return a String about what the ChangeColor animation does
   */
  public String toString() {
    return "Shape " + s.getName() + " changes color from " + s.getColorSet() +
            " to (" + this.red + "," + this.blue + "," + this.green + ") from t=" + this.start +
            " to t=" + this.end;
  }

  /**
   * This method get the change per unit time of this IAnimation
   *
   * @return change per unit time of this IAnimation
   */
  public ArrayList<Float> getChange() {
    change.set(0, (red - s.getRed()) / ((float) (end - start + 1)));
    change.set(1, (blue - s.getBlue()) / ((float) (end - start + 1)));
    change.set(2, (green - s.getGreen()) / ((float) (end - start + 1)));
    return change;
  }

  public float getRed() {
    return this.red;
  }

  public float getGreen() {
    return this.green;
  }

  public float getBlue() {
    return this.blue;
  }
}
