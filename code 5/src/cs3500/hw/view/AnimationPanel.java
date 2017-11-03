import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;


import javax.swing.*;

import cs3500.hw05.model.AnimationModel;
import cs3500.hw05.model.ChangeColor;
import cs3500.hw05.model.IAnimation;
import cs3500.hw05.model.Move;
import cs3500.hw05.model.Oval;
import cs3500.hw05.model.Scale;
import cs3500.hw05.model.Shape;
import cs3500.hw05.model.Rectangle;
import javafx.animation.Animation;

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
    this.timer = new Timer(1000/tick, (ActionListener) this);
  }

  public void draw() {
    timer.start();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for(int i = 0; i < shapes.size(); i++) {
      Shape s = shapes.get(i);
      float x = s.getX();
      float y = (int)s.getY();
      float width = (int)s.getWidth();
      float height = (int)s.getHeight();
      float red = s.getRed();
      float green = s.getGreen();
      float blue = s.getBlue();
      for(IAnimation a: moves) {
        Shape temp = a.getShape();
        if(s.getName().equals(temp.getName())
                && currentTime >= a.getStart()
                && currentTime <= a.getEnd()){
          if(a instanceof Move){
          //  System.out.print("ori x: "+x+"\n");
            x +=  a.getChange().get(0);
          //  System.out.print("changed x: "+x+"\n");
            y += a.getChange().get(1);
          }
          else if(a instanceof Scale) {
         //   System.out.print("ori width: "+width+"\n");
            width += a.getChange().get(0);
         //   System.out.print("changed to "+width+"\n");
            height += a.getChange().get(1);
          }
          else {
           // System.out.print("ori red: "+red+"\n");
            red += (a.getChange().get(0));
            green = green+(a.getChange().get(2));
          //  System.out.print("changed red "+red+"\n");
            blue += (a.getChange().get(1));
           // System.out.print(a.getChange().get(2)+"\n");
          }
        }
      }
      if(currentTime <= s.getDisappears()
              && currentTime >= s.getAppears()
              && s instanceof Rectangle){
        g.setColor(new Color(red, green, blue));
       // System.out.print("rounded= "+Math.round(x));
        g.fillRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
        shapes.set(i, new Rectangle(s.getName(), (float)x, (float)y, (float)width, (float)height, red, blue, green, s.getAppears(), s.getDisappears()));
      }
      else if(currentTime <= s.getDisappears()
              && currentTime >= s.getAppears()
              && s instanceof Oval) {
        g.setColor(new Color(red, green, blue));
        g.fillOval(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
        shapes.set(i, new Oval(s.getName(), (float)x, (float)y, (float)width, (float)height, red, blue, green, s.getAppears(), s.getDisappears()));
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (currentTime < 1000) {
      currentTime++;
      repaint();
    }
    else {
      timer.stop();
    }
  }

  public String toSVG() {
    String result ="";
    for (Shape s: this.shapes) {
      String type;
      String tempResult="";
      if (s instanceof Rectangle) {type = "rect";}
      else {type = "ellipse";}
      ArrayList<IAnimation> allInstructions = new ArrayList<IAnimation>();
      for (IAnimation move : this.moves) {
        if (move.getShape().getName()==s.getName()) {
          allInstructions.add(move);
        }
      }

      tempResult = String.format("<%s id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\"" +
              " fill=\"rgb(%.1f,%.1f,%.1f)\" visibility=\"hidden\" >\n", type, s.getName(),
              Math.round(s.getX()), Math.round(s.getY()), Math.round(s.getWidth()), Math.round(s.getHeight()),
              s.getRed(), s.getGreen(), s.getBlue());
      tempResult+=;

      tempResult+="</"+ type+ ">";
    }

    return "<svg width=\"120\" height=\"120\" viewBox=\"0 0 120 120\"\n" +
            "    xmlns=\"http://www.w3.org/2000/svg\">\n" + result + "</svg>"
  }
}


<circle fill="red" cx="50%" cy="50%" r="30" stroke="black">

<ellipse id="C" cx="500" cy="100" rx="60" ry="30" fill="rgb(0,0,255)" visibility="visible" >


