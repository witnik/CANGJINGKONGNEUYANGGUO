package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

import cs3500.hw05.model.ChangeColor;
import cs3500.hw05.model.IAnimation;
import cs3500.hw05.model.Move;
import cs3500.hw05.model.Oval;
import cs3500.hw05.model.Scale;
import cs3500.hw05.model.Shape;
import cs3500.hw05.model.Rectangle;
import javax.swing.*;


/**
 * This is the view.AnimationPanel class which extends JPanel and implements the ActionListener
 * interface. This class represents the view.AnimationPanel object which are used to output a visual and
 * a SVG format output window. This panel takes in data from the model and transfer them to
 * outputs.
 */
public class AnimationPanel extends JPanel implements ActionListener {
  private ArrayList<Shape> shapes;
  private ArrayList<IAnimation> moves;
  private int tick = 1;
  private Timer timer;
  private int currentTime = 0;

  /**
   * This is the constructor of the view.AnimationPanel class.
   *
   * @param shapes the list of shapes that are in the model
   * @param moves  the list of animation instructions that are in the model
   * @param tick   the speed of the animations
   */
  AnimationPanel(ArrayList<Shape> shapes, ArrayList<IAnimation> moves, int tick) {
    super();
    this.shapes = shapes;
    this.moves = moves;
    this.tick = tick;
    this.timer = new Timer(Math.round(1000/tick), (ActionListener) this);
    this.setPreferredSize(new Dimension(600,600));
  }

  /**
   * This method starts the timer for this panel and start drawing graphs in the visual output
   * window.
   */
  void draw() {
    timer.start();
    currentTime = 0;
  }

  /**
   * This method paints the all the graphs into the visual output window when being called at.
   *
   * @param g this is the graphic object that provides all the frameworks
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (int i = 0; i < shapes.size(); i++) {
      Shape s = shapes.get(i);
      float x = s.getX();
      float y = s.getY();
      float width = s.getWidth();
      float height = s.getHeight();
      float red = s.getRed();
      float green = s.getGreen();
      float blue = s.getBlue();
      for (IAnimation a: moves) {
        Shape temp = a.getShape();
        if (s.getName().equals(temp.getName())
                && currentTime >= a.getStart()
                && currentTime <= a.getEnd()) {
          if (a instanceof Move) {
            x = s.getX() + (((Move)a).getX1() - s.getX()) / (float) (a.getEnd() - currentTime + 1);
            y = s.getY() + (((Move)a).getY1() - s.getY()) / (float) (a.getEnd() - currentTime + 1);
          } else if (a instanceof Scale) {
            width = s.getWidth() + (((Scale)a).getNwidth() - s.getWidth()) / (float) (a.getEnd() - currentTime + 1);
            height = s.getHeight() + (((Scale)a).getNheight() - s.getHeight()) / (float) (a.getEnd() - currentTime + 1);
          } else {
            red = s.getRed() + (((ChangeColor)a).getRed() - s.getRed()) / (float) (a.getEnd() - currentTime + 1);
            green = s.getGreen() + (((ChangeColor)a).getGreen() - s.getGreen()) / (float) (a.getEnd() - currentTime + 1);
            blue = s.getBlue() + (((ChangeColor)a).getBlue() - s.getBlue()) / (float) (a.getEnd() - currentTime + 1);
          }
        }
      }
      if (currentTime <= s.getDisappears()
              && currentTime >= s.getAppears()
              && s instanceof Rectangle) {
        g.setColor(new Color(red, green, blue));
        g.fillRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
        shapes.set(i, new Rectangle(s.getName(), x, y, width,
                height, red, green, blue, s.getAppears(), s.getDisappears()));
      }
      if (currentTime <= s.getDisappears()
              && currentTime >= s.getAppears()
              && s instanceof Oval) {
        g.setColor(new Color(red, green, blue));
        g.fillOval(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
        shapes.set(i, new Oval(s.getName(), x, y, width,
                height, red, green, blue, s.getAppears(), s.getDisappears()));
      }
    }
  }

  /**
   * This is the method that calls the paintComponent method after the time starts. This method
   * redraws all the shapes and creates animation.
   *
   * @param e ActionEvent object that listens to the action
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    ArrayList<Shape> endShapes = shapes;
    endShapes.sort(new endComparator());
    int end = 0;
    if(!endShapes.isEmpty()) {
      end = endShapes.get(endShapes.size() - 1).getDisappears();
    }
    if (currentTime < end) {
      currentTime++;
      repaint();
    } else {
      timer.stop();
    }
  }

  /**
   * This is a AnimationComparator class that implements Comparator interface. This method can help
   * to sort the list.
   */
  private class endComparator implements Comparator<Shape> {
    /**
     * This method compares the disappearing time of two SHape object.
     *
     * @param s1 Shape object 1 that is being compared
     * @param s2 Shape object 2 that is being compared
     * @return the difference between end time of two Shape objects
     */
    public int compare(Shape s1, Shape s2) {
      return s1.getDisappears() - s2.getDisappears();
    }
  }

  /**
   * This is method output all the data into the SVG format. This method outputs a String that is in
   * SVG form.
   *
   * @return String of SVG form
   */
  String toSVG() {
    String result = "";
    for (Shape s : shapes) {
      String start;
      String type;
      String param;
      String xLen;
      String yLen;
      String tempResult = "";
      if (s instanceof Rectangle) {
        start = String.format("<rect id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\"" +
                        " fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\" >\n", s.getName(),
                Math.round(s.getX()), Math.round(s.getY()),
                Math.round(s.getWidth()), Math.round(s.getHeight()),
                Math.round(s.getRed() * 255), Math.round(s.getGreen() * 255),
                Math.round(s.getBlue() * 255));
        type = "rect";
        param = "";
        xLen = "width";
        yLen = "height";
      } else {
        start = String.format("<ellipse id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\"" +
                        " fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\" >\n", s.getName(),
                Math.round(s.getX()), Math.round(s.getY()),
                Math.round(s.getWidth()), Math.round(s.getHeight()),
                Math.round(s.getRed() * 255), Math.round(s.getGreen() * 255),
                Math.round(s.getBlue() * 255));
        type = "ellipse";
        param = "c";
        xLen = "rx";
        yLen = "ry";
      }
      ArrayList<IAnimation> allInstructions = new ArrayList<IAnimation>();
      tempResult += start;
      for (IAnimation move : this.moves) {
        if (move.getShape().getName().equals(s.getName())) {
          allInstructions.add(move);
        }
      }
      tempResult += String.format("<set attributeName=\"visibility\"" +
              " attributeType=\"CSS\" to=\"visible\"" +
              " begin=\"%dms\"/>\n", s.getAppears() * 1000 / this.tick);
      tempResult += String.format("<set attributeName=\"visibility\"" +
              " attributeType=\"CSS\" to=\"" +
              "hidden\" begin=\"%dms\"/>\n", s.getDisappears() * 1000 / this.tick);

      for (IAnimation m : allInstructions) {
        if (m instanceof Move) {
          int x = Math.round(((Move) m).getX1());
          int y = Math.round(((Move) m).getY1());
          tempResult += String.format("<animate attributeType=\"xml\" begin=\"%dms\" " +
                          "dur=\"%dms\" attributeName=\"%sx\"" +
                          " from=\"%d\" to=\"%d\" fill=\"freeze\" />\n",
                  m.getStart() * 1000 / this.tick, (m.getEnd() - m.getStart()) * 1000 / this.tick,
                  param, Math.round(s.getX()),
                  Math.round(x));
          tempResult += String.format("<animate attributeType=\"xml\" begin=\"%dms\" " +
                          "dur=\"%dms\" attributeName=\"%sy\" from=\"%d\"" +
                          " to=\"%d\" fill=\"freeze\" />\n",
                  m.getStart() * 1000 / this.tick, (m.getEnd() - m.getStart()) * 1000 / this.tick,
                  param, Math.round(s.getY()),
                  Math.round(y));
          if (type.equals("rect")) {
            s = new Rectangle(s.getName(), (float) x, (float) y, s.getWidth(), s.getHeight(),
                    s.getRed(), s.getGreen(), s.getBlue(), s.getAppears(), s.getDisappears());
          } else {
            s = new Oval(s.getName(), (float) x, (float) y, s.getWidth(), s.getHeight(),
                    s.getRed(), s.getGreen(), s.getBlue(), s.getAppears(), s.getDisappears());
          }
        } else if (m instanceof ChangeColor) {
          float red = ((ChangeColor) m).getRed();
          float green = ((ChangeColor) m).getGreen();
          float blue = ((ChangeColor) m).getBlue();
          tempResult += String.format("<animate attributeType=\"XML\" attributeName=\"fill\"" +
                          " from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\"\n dur=\"%dms\"" +
                          " begin=\"%dms\" repeatCount=\"0\" fill=\"freeze\" />\n",
                  Math.round(s.getRed() * 255), Math.round(s.getGreen() * 255),
                  Math.round(s.getBlue() * 255), Math.round(red * 255),
                  Math.round(green * 255), Math.round(blue * 255), (m.getEnd() - m.getStart()) * 1000 / this.tick,
                  m.getStart() * 1000 / this.tick);
          if (type.equals("rect")) {
            s = new Rectangle(s.getName(), s.getX(), s.getY(), s.getWidth(), s.getHeight(),
                    red, green, blue, s.getAppears(), s.getDisappears());
          } else {
            s = new Oval(s.getName(), s.getX(), s.getY(), s.getWidth(), s.getHeight(),
                    red, green, blue, s.getAppears(), s.getDisappears());
          }
        } else {
          int width = Math.round(((Scale) m).getNwidth());
          int height = Math.round(((Scale) m).getNheight());
          tempResult += String.format("<animate attributeType=\"XML\" attributeName=\"%s\"" +
                          " from=\"%d\" to=\"%d\"\n dur=\"%dms\" begin=\"%dms\"" +
                          " repeatCount=\"0\" fill=\"freeze\" />\n", xLen,
                  Math.round(s.getWidth()), width, (m.getEnd() - m.getStart()) / this.tick,
                  m.getStart() * 1000 / this.tick);

          tempResult += String.format("<animate attributeType=\"XML\" attributeName=\"%s\"" +
                          " from=\"%d\" to=\"%d\"\n dur=\"%dms\" begin=\"%dms\"" +
                          " repeatCount=\"0\" fill=\"freeze\" />\n", yLen,
                  Math.round(s.getHeight()), height, (m.getEnd() - m.getStart()) * 1000 / this.tick,
                  m.getStart() * 1000 / this.tick);
          if (type.equals("rect")) {
            s = new Rectangle(s.getName(), s.getX(), s.getY(), (float) width, (float) height,
                    s.getRed(), s.getGreen(), s.getBlue(), s.getAppears(), s.getDisappears());
          } else {
            s = new Oval(s.getName(), s.getX(), s.getY(), (float) width, (float) height,
                    s.getRed(), s.getGreen(), s.getBlue(), s.getAppears(), s.getDisappears());
          }
        }
      }
      tempResult += "</" + type + ">\n";
      result += tempResult;
    }

    return "<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
            "    xmlns=\"http://www.w3.org/2000/svg\">\n" + result + "</svg>";
  }
}