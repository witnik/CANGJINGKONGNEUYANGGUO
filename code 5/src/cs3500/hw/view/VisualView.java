package view;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;


import javax.swing.*;

import model.AnimationModel;
import model.IAnimation;
import model.IAnimationModel;
import model.Oval;
import model.Rectangle;
import model.Shape;
import view.AnimationPanel;
import view.IView;

/**
 * This is the view class VisualView that outputs the graph of this model in to the visual output
 * window transfering data from the model.
 */
public class VisualView extends JFrame implements IView {
  private IAnimationModel model;

  /**
   * Constructor of the VisualView class.
   *
   * @param model         the model that are being outputed visually
   * @param tickPerSecond the speed of the animation
   */
  public VisualView(IAnimationModel model, int tickPerSecond) {
    super();
    this.setSize(500, 500);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.model = model;
    ArrayList<Shape> shapes = this.model.getShapes();
    ArrayList<IAnimation> animations = this.model.getAnimation();
    AnimationPanel animationPanel = new AnimationPanel(shapes, animations, tickPerSecond);

    JScrollPane p = new JScrollPane(animationPanel);
    animationPanel.draw();
    this.add(p);
    pack();
  }

  public static void main(String[] args) {
    AnimationModel model = new AnimationModel();
    Shape s1 = new Rectangle("r1", 0.0f, 0.0f, 10.0f, 10.0f, 0.1f, 0.1f, 0.3f, 3, 20);
    Shape s2 = new Oval("r2", 10.0f, 50.0f, 3.0f, 6.0f, 0.1f, 0.1f, 0.3f, 3, 25);
    model.addShape(s1);
    model.addShape(s2);
    model.scale(s2, 20.0f, 10.0f, 6, 10);
    model.move(s2, 10.0f, 100.0f, 6, 11);
    model.move(s1, 50.0f, 60.0f, 5, 19);
    model.changeColor(s1, 0.5f, 0.7f, 0.6f, 6, 8);
    VisualView visual = new VisualView(model, 1);
    visual.makeVisible();
  }

  /**
   * Make the view visible. this should be called after a view is constructed.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Show error message
   *
   * @param error the error message to be displayed.
   */
  @Override
  public void showError(String error) {
    JOptionPane.showMessageDialog(this, error,
            "Error", JOptionPane.ERROR_MESSAGE);
  }
}