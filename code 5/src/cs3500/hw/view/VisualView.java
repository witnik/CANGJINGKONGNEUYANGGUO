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
    this.setSize(800, 800);
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