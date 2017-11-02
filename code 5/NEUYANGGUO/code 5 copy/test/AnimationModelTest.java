import org.junit.Test;

import static org.junit.Assert.assertEquals;

import model.AnimationModel;
import model.IAnimationModel;
import model.Oval;
import model.Rectangle;
import model.Shape;

/**
 * This is the test for AnimationModel class. This test checks if animation model can output the
 * right output and can throw illegal argument when the animation can not take place for the shape.
 */
public class AnimationModelTest {
  private IAnimationModel model = new AnimationModel();
  private Shape rectangle = new Rectangle("r", 10.000,10.0,5.0,
          6.0,3.0, 1.9,2.3,10,40);
  private Shape oval = new Oval("o", 30.0,50.0,2.0,3.0,
          1.0,2.3,5.6,7,220);

  /**
   * This test if the AnimationModel outputs the right String.
   */
  @Test
  public void animation() {
    model.addShape(rectangle);
    model.addShape(oval);
    model.move(rectangle,12.0,43.0,10,15);
    model.move(oval, 50.0,250.0,200,220);
    model.scale(rectangle,12.0,50.0,12,20);
    model.changeColor(oval,2.1,2.2,2.3,11,21);
    model.scale(oval,30.2,13.0,8,17);
    model.animate();
    assertEquals("Shapes:\n" +
            "Name: o\n" +
            "Type: oval\n" +
            "Lower-left corner: (30.0,50.0), xRadius: 2.0, yRadius: 3.0, Color: (1.0,2.3,5.6)\n" +
            "Appears at t=7\n" +
            "Disappears at t=220\n" +
            "\n" +
            "\n" +
            "Name: r\n" +
            "Type: rectangle\n" +
            "Lower-left corner: (10.0,10.0), Width: 5.0, Height: 6.0, Color: (3.0,1.9,2.3)\n" +
            "Appears at t=10\n" +
            "Disappears at t=40\n" +
            "\n" +
            "\n" +
            "Shape o scales from Width: 30.2, Height: 13.0 to Width: 30.2, " +
            "Height: 13.0 from t=8 to t=17\n" +
            "Shape r moves from (12.0,43.0) to (12.0,43.0) from time t=10 to t=15\n" +
            "Shape o changes color from (2.1,2.2,2.3) to (2.1,2.2,2.3) from t=11 to t=21\n" +
            "Shape r scales from Width: 12.0, Height: 50.0 to Width: 12.0, " +
            "Height: 50.0 from t=12 to t=20\n" +
            "Shape o moves from (50.0,250.0) to (50.0,250.0) " +
            "from time t=200 to t=220\n", model.toString());
  }

  /**
   * This test if the AnimationModel throws exception when the animation can not take place for
   * the Shape object.
   * @throws Exception if the Animation is invalid for the Shape object
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidAnimation1() throws Exception {
    rectangle = new Rectangle("r", 10.0,10.0,5.0,
            6.0,3.0, 1.9,2.3,10,40);
    model.addShape(rectangle);
    model.move(rectangle,12.0,43.0,1,12);
  }

  /**
   * This test if the AnimationModel throws exception when the animation can not take place for
   * the Shape object.
   * @throws Exception if the Animation is invalid for the Shape obejct
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidAnimation2() throws Exception {
    rectangle = new Rectangle("r", 10.0,10.0,5.0,
            6.0,3.0, 1.9,2.3,10,40);
    model.addShape(rectangle);
    model.move(rectangle,12.0,43.0,10,13);
    model.move(rectangle,33.0,4.0,11,16);
  }

  /**
   * This test if the AnimationModel throws exception when the animation can not take place for
   * the Shape object.
   * @throws Exception if the Animation is invalid for the Shape obejct
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidAnimation3() throws Exception {
    oval = new Oval("o", 30.0,50.0,2.0,3.0,
            1.0,2.3,5.6,7,220);
    model.addShape(oval);
    model.changeColor(oval,12.0,43.0, 3.0,11,13);
    model.changeColor(oval,33.0,4.0, 5.0,10,16);
  }

  /**
   * This test if the AnimationModel throws exception when the animation can not take place for
   * the Shape object.
   * @throws Exception if the Animation is invalid for the Shape obejct
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidAnimation4() throws Exception {
    rectangle = new Rectangle("r", 10.0,10.0,5.0,
            6.0,3.0, 1.9,2.3,10,40);
    model.addShape(rectangle);
    model.scale(rectangle, 12.0, 43.0, 20, 25);
    model.scale(rectangle, 33.0, 4.0, 19, 22);
  }

  /**
   * This test if the AnimationModel throws exception when the animation is trying to act on a
   * Shape object that is not in the AnimationModel.
   */
  @Test(expected = IllegalArgumentException.class)
  public void shapeDoesNotExis1() throws Exception {
    rectangle = new Rectangle("r", 10.0,10.0,5.0,
            6.0,3.0, 1.9,2.3,10,40);
    model.addShape(rectangle);
    model.scale(oval,23.0,60.2,30,100);
  }

  /**
   * This test if the AnimationModel throws exception when the animation is trying to act on a
   * Shape object that is not in the AnimationModel.
   */
  @Test(expected = IllegalArgumentException.class)
  public void shapeDoesNotExist2() throws Exception {
    rectangle = new Rectangle("r", 10.0,10.0,5.0,
            6.0,3.0, 1.9,2.3,10,40);
    model.addShape(rectangle);
    model.changeColor(oval,23.0,60.2,40.3,30,100);
  }

  /**
   * This test if the AnimationModel throws exception when the animation is trying to act on a
   * Shape object that is not in the AnimationModel.
   */
  @Test(expected = IllegalArgumentException.class)
  public void shapeDoesNotExist3() throws Exception {
    oval = new Oval("o", 30.0,50.0,2.0,3.0,
            1.0,2.3,5.6,7,220);
    model.addShape(oval);
    model.move(rectangle,23.0,60.2,30,100);
  }
}