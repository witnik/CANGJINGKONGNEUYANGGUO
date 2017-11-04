package view;

import java.awt.*;
import java.util.ArrayList;


import javax.swing.*;

import cs3500.hw05.model.IAnimation;
import cs3500.hw05.model.IAnimationModel;
import cs3500.hw05.model.Shape;
/**
 * This is the view class view.VisualView that outputs the graph of this model in to the visual output
 * window transfering data from the model.
 */
public class VisualView extends JFrame implements IView {
  private IAnimationModel model;

  /**
   * Constructor of the view.VisualView class.
   *
   * @param model         the model that are being outputed visually
   * @param tickPerSecond the speed of the animation
   */
  public VisualView(IAnimationModel model, int tickPerSecond) {
    super();
    this.setPreferredSize(new Dimension(500, 500));
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.model = model;
    ArrayList<Shape> shapes = this.model.getShapes();
    ArrayList<IAnimation> animations = this.model.getAnimation();
    AnimationPanel animationPanel = new AnimationPanel(shapes, animations, tickPerSecond);
    JScrollPane p = new JScrollPane(animationPanel);
    this.add(p);
    animationPanel.draw();
    pack();
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