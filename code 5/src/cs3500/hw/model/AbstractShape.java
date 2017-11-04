package model;

/**
 * This is an abstract class that implements the Shape interface.
 */
public abstract class AbstractShape implements Shape {

  String name;
  float cRed;
  float cBlue;
  float cGreen;
  int appears;
  int disappears;
  private IAnimation[] animationsMove;
  private IAnimation[] animationsColor;
  private IAnimation[] animationsScale;

  /**
   * This is the constructor for the abstractShape class. This constructor also construct three list
   * that represent each Animation object.
   *
   * @param name       name of the AbstractShape object
   * @param cRed       red data of the color of the AbstractShape object
   * @param cBlue      blue data of the color of the AbstractShape object
   * @param cGreen     green data of the color of the AbstractShape object
   * @param appears    the time when this shapes appears
   * @param disappears the time when this shape disappears
   */
  AbstractShape(String name, float cRed, float cGreen,
                float cBlue, int appears, int disappears) {
    this.name = name;
    this.cRed = cRed;

    this.cBlue = cBlue;
    this.cGreen = cGreen;
    this.appears = appears;
    this.disappears = disappears;
    animationsMove = new IAnimation[this.disappears - this.appears + 1];
    animationsColor = new IAnimation[this.disappears - this.appears + 1];
    animationsScale = new IAnimation[this.disappears - this.appears + 1];
  }

  /**
   * This is a getter method that gets the name of the shape.
   *
   * @return name of the AbstractShape object
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * This is a getter method that gets the X parameter of the shape.
   *
   * @return X parameter of the AbstractShape object
   */
  @Override
  public abstract float getX();

  /**
   * This is a getter method that gets the Y parameter of the shape.
   *
   * @return X parameter of the AbstractShape object
   */
  @Override
  public abstract float getY();

  /**
   * This is a getter method that gets the width of the shape.
   *
   * @return width of the AbstractShape object
   */
  @Override
  public abstract float getWidth();

  /**
   * This is a getter method that gets the height of the shape.
   *
   * @return height of the AbstractShape object
   */
  @Override
  public abstract float getHeight();

  /**
   * This is a getter method that gets all the color data and put them into a String.
   *
   * @return a String that contains three parameters for the Color of the AbstractShape object
   */
  @Override
  public String getColorSet() {
    return "(" + this.cRed + "," + this.cBlue + "," + this.cGreen + ")";
  }

  /**
   * This is a getter method that gets the appear time of the Shape object.
   *
   * @return the appear time of the Shape object
   */
  public int getAppears() {
    return this.appears;
  }

  /**
   * This is a getter method that gets the disappear time of the Shape object.
   *
   * @return the disappear time of the Shape object
   */
  public int getDisappears() {
    return this.disappears;
  }

  /**
   * This method moves a AbstractShape object from one posn to another.
   *
   * @param x1    x of the posn of the AbstractShape object
   * @param y1    y of the posn of the AbstractShape object
   * @param start when does the move animation starts
   * @param end   when does the move animation ends
   */
  @Override
  public abstract void move(float x1, float y1, int start, int end);

  /**
   * This method changes the color of a AbstractShape object.
   *
   * @param red   red color data of the color
   * @param blue  blue color data of the color
   * @param green green color data of the color
   * @param start when does the changeColor animation starts
   * @param end   when does the changeColor animation ends
   */
  @Override
  public void changeColor(float red, float blue, float green, int start, int end) {
    this.cRed = red;
    this.cBlue = blue;
    this.cGreen = green;
  }

  /**
   * This method scales which means changes the width and height of the AbstractShape object.
   *
   * @param width  the width of the AbstractShape object
   * @param height the height of the AbstractShape object
   * @param start  when does the scale animation starts
   * @param end    when does the scale animation ends
   */
  @Override
  public abstract void scale(float width, float height, int start, int end);

  /**
   * This method checks if the animation is valid to proceed for this shape.
   *
   * @param a the animation that are passed into the AbstractShape object
   * @return whether this animation is valid for the AbstractShape object or not
   */
  @Override
  public boolean validAnimation(IAnimation a) {
    if (a.getStart() < this.appears || a.getEnd() > this.disappears) {
      return false;
    }
    if (a instanceof Move) {
      for (int i = ((Move) a).start - this.appears; i <= ((Move) a).end - this.appears; i++) {
        if (animationsMove[i] != null) {
          return false;
        }
      }
      for (int i = ((Move) a).start - this.appears; i <= ((Move) a).end - this.appears; i++) {
        animationsMove[i] = a;
      }
    } else if (a instanceof ChangeColor) {
      ChangeColor temp = (ChangeColor) a;
      for (int i = temp.start - this.appears; i <= temp.end - this.appears; i++) {
        if (animationsColor[i] != null) {
          return false;
        }
      }
      for (int i = temp.start - this.appears; i <= temp.end - this.appears; i++) {
        animationsColor[i] = a;
      }
    } else if (a instanceof Scale) {
      Scale temp = (Scale) a;
      for (int i = temp.start - this.appears; i <= temp.end - this.appears; i++) {
        if (animationsScale[i] != null) {
          return false;
        }
      }
      for (int i = temp.start - this.appears; i <= temp.end - this.appears; i++) {
        animationsScale[i] = a;
      }
    } else {
      return false;
    }
    return true;
  }

  /**
   * This method outputs a String that describes the AbstractShape object.
   *
   * @return a String that describes the AbstractShape object
   */
  public abstract String toString();

  /**
   * This is a getter method that gets the red color of the Shape object
   *
   * @return the red color data a of the Shape object
   */
  public float getRed() {
    return this.cRed;
  }

  /**
   * This is a getter method that gets the green color of the Shape object
   *
   * @return the green color data a of the Shape object
   */
  public float getGreen() {
    return this.cGreen;
  }

  /**
   * This is a getter method that gets the blue color of the Shape object
   *
   * @return the blue color data a of the Shape object
   */
  public float getBlue() {
    return this.cBlue;
  }
}
