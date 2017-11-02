package model;

import java.util.ArrayList;

/**
 * This is the interface for all the Animation objects. This interface helps an animation to act on
 * certain shapes, gets its parameters, and outputs the animation.
 */
public interface IAnimation {

  /**
   * This method proceed the animation on the Shape object.
   */
  void act();

  /**
   * This method is a getter that gets the start time of an animation.
   *
   * @return the start time of an animation
   */
  int getStart();

  /**
   * This method is a getter that gets the end time of an animation.
   *
   * @return the end time of an animation
   */
  int getEnd();

  /**
   * This method outputs what the animation does to an object.
   *
   * @return a String about what the animation does
   */
  String toString();

  /**
   * This method get the shape of this object
   * @return the shape of this object
   */
  Shape getShape();

  /**
   * This method get the change per unit time of this IAnimation
   * @return change per unit time of this IAnimation
   */
  ArrayList<Float> getChange();
}
