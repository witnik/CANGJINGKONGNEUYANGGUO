package model;

public class Oval extends AbstractShape {

  private String name;
  private float x;
  private float y;
  private float xRadius;
  private float yRadius;

  /**
   * This is the constructor for Oval class.
   *
   * @param name       name of the Oval object
   * @param x          x value of the center of hte Oval
   * @param y          y value of the center of the Oval
   * @param xRadius    x value of the radius of the Oval
   * @param yRadius    y value of the radius of the Oval
   * @param cRed       red data of the color of the Oval object
   * @param cBlue      blue data of the color of the Oval object
   * @param cGreen     green data of the color of the Oval object
   * @param appears    when the Oval object appears
   * @param disappears when the Oval object disappears
   */
  public Oval(String name, float x, float y,
              float xRadius, float yRadius, float cRed, float cBlue,
              float cGreen, int appears, int disappears) {
    super(name, cRed, cBlue, cGreen, appears, disappears);
    this.name = name;
    this.x = x;
    this.y = y;
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * This is a getter method that gets the X parameter of the shape.
   *
   * @return X parameter of the Oval object
   */
  @Override
  public float getX() {
    return this.x;
  }

  /**
   * This is a getter method that gets the Y parameter of the shape.
   *
   * @return X parameter of the Oval object
   */
  @Override
  public float getY() {
    return this.y;
  }

  /**
   * This is a getter method that gets the x value of the radius of the shape.
   *
   * @return x value of the radius of the Oval object
   */
  @Override
  public float getWidth() {
    return this.xRadius;
  }

  /**
   * This is a getter method that gets the height of the shape.
   *
   * @return height of the Oval object
   */
  @Override
  public float getHeight() {
    return this.yRadius;
  }

  /**
   * This method moves a Oval object from one posn to another.
   *
   * @param x1    x of the posn of the Oval object
   * @param y1    y of the posn of the Oval object
   * @param start when does the move animation starts
   * @param end   when does the move animation ends
   */
  @Override
  public void move(float x1, float y1, int start, int end) {
    this.x = x1;
    this.y = y1;
  }

  /**
   * This method scales which means changes the width and height of the Oval object.
   *
   * @param xRadius the width of the Oval object
   * @param yRadius the height of the Oval object
   * @param start   when does the scale animation starts
   * @param end     when does the scale animation ends
   */
  @Override
  public void scale(float xRadius, float yRadius, int start, int end) {
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * This method outputs a String that describes the Rectangle object.
   *
   * @return a String that describes the Oval object
   */
  public String toString() {
    return "Name: " + this.name + "\n" +
            "Type: oval\n" +
            "Lower-left corner: (" + this.x + "," + this.y + "), " +
            "xRadius: " + this.xRadius + ", yRadius: " + this.yRadius + ", Color: (" +
            this.cRed + "," + this.cBlue + "," + this.cGreen + ")\n" +
            "Appears at t=" + this.appears + "s\n" +
            "Disappears at t=" + this.disappears + "s\n\n";
  }
}
