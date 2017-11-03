package model;

import java.util.ArrayList;

/**
 * This is the interface of AnimationModel. This interface controls the whole model. This interface
 * contains animation methods and animates those methods Animation objects.
 */
public interface IAnimationModel {

  /**
   * This method moves a Shape object from one posn to another. This method will throw illegal
   * argument if the move can not happen for the Shape object.
   *
   * @param s     the Shape object that this move method will take place on
   * @param x1    x value for the new posn
   * @param y1    y value for the new posn
   * @param start when does the move method starts
   * @param end   when does the move method ends
   */
  void move(Shape s, float x1, float y1, int start, int end);

  /**
   * This method changes the color of a Shape object. This method will throw illegal argument if the
   * change color animation can not happen for the Shape object.
   *
   * @param s     the Shape object color changing will take place on
   * @param red   red color data of the color
   * @param blue  blue color data of the color
   * @param green green color data of the color
   * @param start when does the changeColor animation starts
   * @param end   when does the changeColor animation ends
   */
  void changeColor(Shape s, float red, float blue, float green, int start, int end);

  /**
   * This method scales which means changes the width and height of the Shape object. This method
   * will throw illegal argument exception if the scale can not happen for hte Shape object.
   *
   * @param s      The Shape object scale will take place on
   * @param width  the width of the Shape object
   * @param height the height of the Shape object
   * @param start  when does the scale animation starts
   * @param end    when does the scale animation ends
   */
  void scale(Shape s, float width, float height, int start, int end);

  /**
   * This method proceeds all the animations with all the Shape objects in a sorted order by their
   * starting time.
   */
  void animate();

  /**
   * This method add a Shape object into the list of shapes.
   *
   * @param s a Shape object that will be added into the model
   */
  void addShape(Shape s);

  /**
   * This method outputs a String that describes all the Shape objects in this model and all the
   * animation that happens in the model.
   *
   * @return a String that describes all the Shape objects and animations
   */
  String toString();

  /**
   * This is a getter method that extracts all the Shape objects in this model
   *
   * @return list contains all the Shape objects
   */
  ArrayList<Shape> getShapes();

  /**
   * This is a getter method that extracts all the IAnimation objects in this model
   *
   * @return list contains all the IAnimation objects
   */
  ArrayList<IAnimation> getAnimation();
}
