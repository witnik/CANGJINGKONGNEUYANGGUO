package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This is AnimationModel class that implements IAnimationModel interface. This class is the model
 * class. This class has a list that contains all the Shape objects and a list that contains all the
 * Animation that will happen. This class also has all the methods that can create an animation and
 * place them into Shape objects. At the end this class will output a String variable that contains
 * all the information about the Shape and all the Animation.
 */
public class AnimationModel implements IAnimationModel {

  private int tick = 1;
  private ArrayList<IAnimation> moves = new ArrayList<>();
  private String shapeOutput = "Shapes:\n";
  private String animationOutput = "";
  private ArrayList<Shape> shapes = new ArrayList<Shape>();

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
  @Override
  public void move(Shape s, float x1, float y1, int start, int end) {
    IAnimation moveA = new Move(s, x1, y1, start, end);
    if (!containShape(s)) {
      throw new IllegalArgumentException("Please add this shape into the model first.");
    }
    if (s.validAnimation(moveA)) {
      moves.add(moveA);
    } else {
      throw new IllegalArgumentException("This animation is taking place right now.");
    }
  }

  /**
   * This is a ShapeComparator class that implements Comparator interface. This method can help to
   * sort the list.
   */
  private class ShapeComparator implements Comparator<Shape> {
    /**
     * This is a compare method that compares the appearing time of two shapes.
     *
     * @param s1 Shape 1 that is being compared
     * @param s2 Shape 2 that is being compared
     * @return the difference between their appearing time
     */
    public int compare(Shape s1, Shape s2) {
      return s1.getAppears() - s2.getAppears();
    }
  }

  /**
   * This is a AnimationComparator class that implements Comparator interface. This method can help
   * to sort the list.
   */
  private class AnimationComparator implements Comparator<IAnimation> {
    /**
     * This method compares the start time of two Animation object.
     *
     * @param a1 Animation object 1 that is being compared
     * @param a2 Animation object 2 that is being compared
     * @return the difference between start time of two Animation objects
     */
    public int compare(IAnimation a1, IAnimation a2) {
      return a1.getStart() - a2.getStart();
    }
  }

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
  @Override
  public void changeColor(Shape s, float red, float blue, float green, int start, int end) {
    IAnimation changeColorA = new ChangeColor(s, red, blue, green, start, end);
    if (!containShape(s)) {
      throw new IllegalArgumentException("Please add this shape into the model first.");
    }
    if (s.validAnimation(changeColorA)) {
      moves.add(changeColorA);
    } else {
      throw new IllegalArgumentException("This animation is taking place right now.");
    }
  }

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
  @Override
  public void scale(Shape s, float width, float height, int start, int end) {
    IAnimation scaleA = new Scale(s, width, height, start, end);
    if (!containShape(s)) {
      throw new IllegalArgumentException("Please add this shape into the model first.");
    }
    if (s.validAnimation(scaleA)) {
      moves.add(scaleA);
    } else {
      throw new IllegalArgumentException("This animation is taking place right now.");
    }
  }

  /**
   * This method add a Shape object into the list of shapes.
   *
   * @param s a Shape object that will be added into the model
   */
  public void addShape(Shape s) {
    if (!containShape(s)) {
      shapes.add(s);
    }
  }

  /**
   * This method checks if the given Shape is already in the model, or in the list of Shape
   * objects.
   *
   * @param s the Shape object that will be checked for duplicates
   * @return whether the Shape is already in the model
   */
  private boolean containShape(Shape s) {
    for (Shape temp : shapes) {
      if (temp.getName().compareTo(s.getName()) == 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method proceeds all the animations with all the Shape objects in a sorted order by their
   * starting time.
   */
  public void animate() {
    shapes.sort(new ShapeComparator());
    for (Shape s : shapes) {
      shapeOutput = shapeOutput + s.toString() + "\n";
    }

    moves.sort(new AnimationComparator());

    for (IAnimation a : moves) {
      a.act();
      animationOutput = animationOutput + a.toString() + "\n";
    }

  }

  /**
   * This method outputs a String that describes all the Shape objects in this model and all the
   * animation that happens in the model.
   *
   * @return a String that describes all the Shape objects and animations
   */
  public String toString() {
    return shapeOutput + animationOutput;
  }

  /**
   * This is a getter method that extracts all the Shape objects in this model
   * @return list contains all the Shape objects
   */
  @Override
  public ArrayList<Shape> getShape() {
    return this.shapes;
  }

  /**
   * This is a getter method that extracts all the IAnimation objects in this model
   * @return list contains all the IAnimation objects
   */
  @Override
  public ArrayList<IAnimation> getAnimation() {
    return moves;
  }
}
