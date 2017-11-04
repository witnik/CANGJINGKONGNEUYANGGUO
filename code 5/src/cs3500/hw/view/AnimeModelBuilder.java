package cs3500.hw.view;

import cs3500.hw.model.AnimationModel;
import cs3500.hw.model.Oval;
import cs3500.hw.model.Rectangle;
import cs3500.hw.model.Shape;

/**
 * This is the AnimeModelBuilder class that construct a build for the main class.
 */
public class AnimeModelBuilder implements TweenModelBuilder<AnimationModel> {
  private AnimationModel model;

  /**
   * This is the constructor for AnimeModelBuilder.
   */
  AnimeModelBuilder() {
    this.model = new AnimationModel();
  }

  /**
   * This is the alternate constructor for AnimeModelBuilder. This constrctur takes in a model.
   * @param model model that will be construct
   */
  AnimeModelBuilder(AnimationModel model) {this.model = model;}

  /**
   * Add a new oval to the model with the given specifications
   *
   * @param name        the unique name given to this shape
   * @param cx          the x-coordinate of the center of the oval
   * @param cy          the y-coordinate of the center of the oval
   * @param xRadius     the x-radius of the oval
   * @param yRadius     the y-radius of the oval
   * @param red         the red component of the color of the oval
   * @param green       the green component of the color of the oval
   * @param blue        the blue component of the color of the oval
   * @param startOfLife the time tick at which this oval appears
   * @param endOfLife   the time tick at which this oval disappears
   * @return the builder object
   */
  @Override
  public TweenModelBuilder<AnimationModel> addOval(String name, float cx, float cy, float xRadius,
                                                   float yRadius, float red, float green,
                                                   float blue, int startOfLife, int endOfLife) {
    Oval shape = new Oval(name, cx, cy, xRadius, yRadius, red, green, blue, startOfLife, endOfLife);
    this.model.addShape(shape);
    return this;
  }

  /**
   * Add a new rectangle to the model with the given specifications
   *
   * @param name        the unique name given to this shape
   * @param lx          the x-coordinate of the lower left corner of the
   *                    rectangle
   * @param ly          the y-coordinate of the lower left corner of the
   *                    rectangle
   * @param width       the width of the rectangle
   * @param height      the height of the rectangle
   * @param red         the red component of the color of the rectangle
   * @param green       the green component of the color of the rectangle
   * @param blue        the blue component of the color of the rectangle
   * @param startOfLife the time tick at which this rectangle appears
   * @param endOfLife   the time tick at which this rectangle disappears
   * @return the builder object
   */
  @Override
  public TweenModelBuilder<AnimationModel> addRectangle(String name, float lx, float ly,
                                                        float width, float height, float red,
                                                        float green, float blue, int startOfLife,
                                                        int endOfLife) {
    Rectangle shape = new Rectangle(name, lx, ly, width, height, red, green,
            blue, startOfLife, endOfLife);
    this.model.addShape(shape);
    return this;
  }

  /**
   * Move the specified shape to the given position during the given time
   * interval
   *
   * @param name      the unique name of the shape to be moved
   * @param moveFromX the x-coordinate of the initial position of this shape.
   *                  What this x-coordinate represents depends on the shape.
   * @param moveFromY the y-coordinate of the initial position of this shape.
   *                  what this y-coordinate represents depends on the shape.
   * @param moveToX   the x-coordinate of the final position of this shape. What
   *                  this x-coordinate represents depends on the shape.
   * @param moveToY   the y-coordinate of the final position of this shape. what
   *                  this y-coordinate represents depends on the shape.
   * @param startTime the time tick at which this movement should start
   * @param endTime   the time tick at which this movement should end
   * @return a model builder that consist the model with this after-changed shape
   */
  @Override
  public TweenModelBuilder<AnimationModel> addMove(String name, float moveFromX, float moveFromY,
                                                   float moveToX, float moveToY, int startTime,
                                                   int endTime) {
    Shape s = this.model.getShape(name);
    this.model.move(s, moveToX, moveToY, startTime, endTime);
    return this;
  }

  /**
   * Change the color of the specified shape to the new specified color in the
   * specified time interval
   *
   * @param name      the unique name of the shape whose color is to be changed
   * @param oldR      the r-component of the old color
   * @param oldG      the g-component of the old color
   * @param oldB      the b-component of the old color
   * @param newR      the r-component of the new color
   * @param newG      the g-component of the new color
   * @param newB      the b-component of the new color
   * @param startTime the time tick at which this color change should start
   * @param endTime   the time tick at which this color change should end
   * @return a modelbuilder that consist the model with this after-changed shape
   */
  @Override
  public TweenModelBuilder<AnimationModel> addColorChange(String name, float oldR, float oldG,
                                                          float oldB, float newR, float newG,
                                                          float newB, int startTime, int endTime) {
    Shape s = this.model.getShape(name);
    this.model.changeColor(s, newR, newB, newG, startTime, endTime);
    return this;
  }

  /**
   * Change the x and y extents of this shape from the specified extents to the
   * specified target extents. What these extents actually mean depends on the
   * shape, but these are roughly the extents of the box enclosing the shape
   *
   * @param name name of the shape whose scale is about to be change
   * @param fromSx the old width
   * @param fromSy the old height
   * @param toSx the new width
   * @param toSy the new height
   * @param startTime the stating time
   * @param endTime the ending time
   * @return a modelbuilder that consist the model with this after-changed shape
   */
  @Override
  public TweenModelBuilder<AnimationModel> addScaleToChange(String name, float fromSx,
                                                            float fromSy, float toSx, float toSy,
                                                            int startTime, int endTime) {
    Shape s = this.model.getShape(name);
    this.model.scale(s, toSx, toSy, startTime, endTime);
    return this;
  }

  /**
   * Return the model built so far
   *
   * @return the model that was constructed so far
   */
  @Override
  public AnimationModel build() {
    return this.model;
  }
}