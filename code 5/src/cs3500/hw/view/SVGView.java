package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.hw05.model.IAnimation;
import cs3500.hw05.model.IAnimationModel;
import cs3500.hw05.model.Shape;

/**
 * This is the view class that outputs the SVG file.
 *
 */
public class SVGView extends JFrame implements IView {

  private AnimationPanel panel;
  /**
   * This is the constructor of the view.SVGView class.
   *
   * @param model         the model that are being outputed
   * @param tickPerSecond the speed of the output
   */
  public SVGView(IAnimationModel model, int tickPerSecond) {
    super();
    this.setPreferredSize(new Dimension(500,500));
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

  /**
   * This is a getter method that gets the SVG format output.
   * @return the SVG format output
   */
  public String getSVG() {
    String copy = this.panel.toSVG();
    return  copy;
  }
}
