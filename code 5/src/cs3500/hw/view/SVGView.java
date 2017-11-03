package view;

import java.util.ArrayList;

import javax.swing.*;

import model.AnimationModel;
import model.IAnimation;
import model.IAnimationModel;
import model.Oval;
import model.Rectangle;
import model.Shape;

/**
 * This is the view class that outputs the SVG file.
 */
public class SVGView extends JFrame implements IView {

  private AnimationPanel panel;
  /**
   * This is the constructor of the SVGView class.
   *
   * @param model         the model that are being outputed
   * @param tickPerSecond the speed of the output
   */
  public SVGView(IAnimationModel model, int tickPerSecond) {
    super();
    this.setSize(500, 500);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    ArrayList<Shape> shapes = model.getShapes();
    ArrayList<IAnimation> animations = model.getAnimation();
    panel = new AnimationPanel(shapes, animations, tickPerSecond);

    JLabel text = new JLabel();
    text.setText(panel.toSVG());
    panel.add(text);

    JScrollPane p = new JScrollPane(panel);
    this.add(p);
    pack();
  }

  /**
   * This is the method that makes the output window visible.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Show error message.
   *
   * @param error the error message to be displayed.
   */
  @Override
  public void showError(String error) {
    JOptionPane.showMessageDialog(this, error,
            "Error", JOptionPane.ERROR_MESSAGE);
  }
}