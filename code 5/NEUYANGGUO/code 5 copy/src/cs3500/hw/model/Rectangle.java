package model;

/**
 * This class extends from the AbstractShape class. This class inherent most the method and creates
 * a Rectangle object.
 */
public class Rectangle extends AbstractShape {
  private float x;
  private float y;
  private float width;
  private float height;

  /**
   * This is the constructor for Rectangle class. This constructor inherent parameters from
   * AbstractShape class while adding a few parameters from its own class.
   *
   * @param name       name of the Rectangle object
   * @param x          x value of the posn of the Rectangle object
   * @param y          y value of the posn of the Rectangle object
   * @param width      width of the Rectangle object
   * @param height     height of the Rectangle object
   * @param cRed       red data of the color of the Rectangle object
   * @param cBlue      blue data of the color of the Rectangle object
   * @param cGreen     green data of the color of the Rectangle object
   * @param appears    when the Rectangle object appears
   * @param disappears when the Rectangle object disappears
   */
  public Rectangle(String name, float x, float y,
                   float width, float height, float cRed,
                   float cBlue, float cGreen, int appears, int disappears) {
    super(name, cRed, cBlue, cGreen, appears, disappears);
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * This is a getter method that gets the X parameter of the shape.
   *
   * @return X parameter of the Rectangle object
   */
  public float getX() {
    return this.x;
  }

  /**
   * This is a getter method that gets the Y parameter of the shape.
   *
   * @return X parameter of the Rectangle object
   */
  public float getY() {
    return this.y;
  }

  /**
   * This is a getter method that gets the width of the shape.
   *
   * @return width of the Rectangle object
   */
  public float getWidth() {
    return this.width;
  }

  /**
   * This is a getter method that gets the height of the shape.
   *
   * @return height of the Rectangle object
   */
  public float getHeight() {
    return this.height;
  }

  /**
   * This method moves a Rectangle object from one posn to another.
   *
   * @param x1    x of the posn of the Rectangle object
   * @param y1    y of the posn of the Rectangle object
   * @param start when does the move animation starts
   * @param end   when does the move animation ends
   */
  @Override
  public void move(float x1, float y1, int start, int end) {
    this.x = x1;
    this.y = y1;
  }

  /**
   * This method scales which means changes the width and height of the Rectangle object.
   *
   * @param sWidth  the width of the Rectangle object
   * @param sHeight the height of the Rectangle object
   * @param start   when does the scale animation starts
   * @param end     when does the scale animation ends
   */
  @Override
  public void scale(float sWidth, float sHeight, int start, int end) {
    this.width = sWidth;
    this.height = sHeight;
  }

  /**
   * This method outputs a String that describes the Rectangle object.
   *
   * @return a String that describes the Rectangle object
   */
  public String toString() {
    return "Name: " + this.name + "\n" +
            "Type: rectangle\n" +
            "Lower-left corner: (" + this.x + "," + this.y + "), " +
            "Width: " + this.width + ", Height: " + this.height + ", Color: (" +
            this.cRed + "," + this.cBlue + "," + this.cGreen + ")\n" +
            "Appears at t=" + this.appears + "s\n" +
            "Disappears at t=" + this.disappears + "s\n\n";
  }
}
