package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.*;

import model.ChangeColor;
import model.IAnimation;
import model.Move;
import model.Oval;
import model.Scale;
import model.Shape;
import model.Rectangle;

public class AnimationPanel extends JPanel implements ActionListener {
  ArrayList<Shape> shapes;
  ArrayList<IAnimation> moves;
  int tick = 1;
  Timer timer;
  int currentTime = 0;

  public AnimationPanel(ArrayList<Shape> shapes, ArrayList<IAnimation> moves, int tick) {
    super();
    this.shapes = shapes;
    this.moves = moves;
    this.tick = tick;
    this.timer = new Timer(1000 / tick,this);
  }

  public void draw() {
    timer.start();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (int i = 0; i < shapes.size(); i++) {
      Shape s = shapes.get(i);
      float x = s.getX();
      float y = (int) s.getY();
      float width = (int) s.getWidth();
      float height = (int) s.getHeight();
      float red = s.getRed();
      float green = s.getGreen();
      float blue = s.getBlue();
      for (IAnimation a : moves) {
        Shape temp = a.getShape();
        if (s.getName().equals(temp.getName())
                && currentTime >= a.getStart()
                && currentTime <= a.getEnd()) {
          if (a instanceof Move) {
            //  System.out.print("ori x: "+x+"\n");
            x += a.getChange().get(0);
            //  System.out.print("changed x: "+x+"\n");
            y += a.getChange().get(1);
          } else if (a instanceof Scale) {
            //   System.out.print("ori width: "+width+"\n");
            width += a.getChange().get(0);
            //   System.out.print("changed to "+width+"\n");
            height += a.getChange().get(1);
          } else {
            // System.out.print("ori red: "+red+"\n");
            red += (a.getChange().get(0));
            green = green + (a.getChange().get(2));
            //  System.out.print("changed red "+red+"\n");
            blue += (a.getChange().get(1));
            // System.out.print(a.getChange().get(2)+"\n");
          }
        }
      }
      if (currentTime <= s.getDisappears()
              && currentTime >= s.getAppears()
              && s instanceof Rectangle) {
        g.setColor(new Color(red, green, blue));
        // System.out.print("rounded= "+Math.round(x));
        g.fillRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
        shapes.set(i, new Rectangle(s.getName(), (float) x, (float) y, (float) width, (float) height, red, blue, green, s.getAppears(), s.getDisappears()));
      } else if (currentTime <= s.getDisappears()
              && currentTime >= s.getAppears()
              && s instanceof Oval) {
        g.setColor(new Color(red, green, blue));
        g.fillOval(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
        shapes.set(i, new Oval(s.getName(), (float) x, (float) y, (float) width, (float) height, red, blue, green, s.getAppears(), s.getDisappears()));
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (currentTime < 1000) {
      currentTime++;
      repaint();
    } else {
      timer.stop();
    }
  }

  public String toSVG() {
    String result = "";
    for (Shape s : this.shapes) {
      String type;
      String tempResult = "";
      if (s instanceof Rectangle) {
        type = "rect";
      } else {
        type = "ellipse";
      }
      ArrayList<IAnimation> allInstructions = new ArrayList<IAnimation>();
      for (IAnimation move : this.moves) {
        if (move.getShape().getName() == s.getName()) {
          allInstructions.add(move);
        }
      }

      tempResult = String.format("<%s id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\"" +
                      " fill=\"rgb(%.1f,%.1f,%.1f)\" visibility=\"hidden\" >\n", type, s.getName(),
              Math.round(s.getX()), Math.round(s.getY()), Math.round(s.getWidth()), Math.round(s.getHeight()),
              s.getRed(), s.getGreen(), s.getBlue());
      for (IAnimation m : allInstructions) {
        if (m instanceof Move) {
          int x = Math.round(m.getChange().get(0) * (m.getEnd() - m.getStart() + 1));
          int y = Math.round(m.getChange().get(1) * (m.getEnd() - m.getStart() + 1));
          tempResult = String.format("<path d=\"L%d,%d\" stroke=\"white\" " +
                  "fill=\"none\" id=\"movePath\" />", x, y) + tempResult;
          tempResult += String.format("<animateMotion dur=\"%ds\" repeatCount=\"indefinite\"" +
                  " begin=\"%ds\">\n <mpath xlink:href=\"#movePath\"/>\n" +
                  "</animateMotion>", m.getEnd() - m.getStart(), m.getStart());
        } else if (m instanceof ChangeColor) {
          float red = m.getChange().get(0) * (float)(m.getEnd() - m.getStart() + 1);
          float green = m.getChange().get(1) * (float)(m.getEnd() - m.getStart() + 1);
          float blue = m.getChange().get(1) * (float)(m.getEnd() - m.getStart() + 1);
          tempResult += String.format("<animateColor attributeName=\"fill\"" +
                          " attributeType=\"XML\"\n from=\"rgb(%.1f,%.1f,%.1f)\"" +
                          " to=\"rgb(%.1f,%.1f,%.1f)\" dur=\"%ds\"" +
                          " begin=\"%ds\" repeatCount=\"indefinite\"/>",
                  s.getRed(), s.getGreen(), s.getBlue(), red, green, blue,
                  m.getEnd() - m.getStart(), m.getStart());
        } else {
          int width = Math.round(m.getChange().get(0) * (m.getEnd() - m.getStart() + 1));
          int height = Math.round(m.getChange().get(1) * (m.getEnd() - m.getStart() + 1));
          tempResult += String.format("<animate attributeType=\"XML\" attributeName=\"width\"" +
                          " from=\"%d\" to=\"%d\"\n dur=\"%d\" begin=\"%ds\" repeatCount=\"indefinite\"/>",
                  Math.round(s.getWidth()), width, m.getEnd() - m.getStart(), m.getStart());

          tempResult += String.format("<animate attributeType=\"XML\" attributeName=\"height\"" +
                          " from=\"%d\" to=\"%d\"\n dur=\"%d\" begin=\"%ds\"" +
                          " repeatCount=\"indefinite\"/>",
                  Math.round(s.getHeight()), height, m.getEnd() - m.getStart(), m.getStart());
        }
      }
      tempResult += "</" + type + ">\n";
      result += tempResult;
    }

    return "<svg width=\"120\" height=\"120\" viewBox=\"0 0 120 120\"\n" +
            "    xmlns=\"http://www.w3.org/2000/svg\">\n" + result + "</svg>";
  }
}