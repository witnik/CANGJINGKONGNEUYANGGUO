package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import model.IAnimation;
import model.Rectangle;
import model.Move;
import model.Oval;
import model.Scale;
import model.Shape;

public class AnimationPanel extends JPanel implements ActionListener {
  ArrayList<Shape> shapes;
  ArrayList<IAnimation> moves;
  int tick = 1;
  Timer timer = new Timer(1000, (ActionListener) this);
  int currentTime = 0;
  public AnimationPanel(ArrayList<Shape> shapes, ArrayList<IAnimation> moves, int tick) {
    super();
    this.shapes = shapes;
    this.moves = moves;
    this.tick = tick;
  }

  public void draw() {
    timer.start();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for(int i = 0; i < shapes.size(); i++) {
      Shape s = shapes.get(i);
      int x = (int)s.getX();
      int y = (int)s.getY();
      int width = (int)s.getWidth();
      int height = (int)s.getHeight();
      float red = s.getRed();
      float green = s.getGreen();
      float blue = s.getBlue();
      for(IAnimation a: moves) {
        Shape temp = a.getShape();
        if(s.getName().equals(temp.getName())
                && currentTime >= a.getStart()
                && currentTime <= a.getEnd()){
          if(a instanceof Move){
            x += (int)(1 * a.getChange().get(0));
            y += (int)(1 * a.getChange().get(1));
          }
          else if(a instanceof Scale) {
            width += (int)(1 * a.getChange().get(0));
            height += (int)(1 * a.getChange().get(1));
          }
          else {
            red += (a.getChange().get(0));
            green += (a.getChange().get(1));
            blue += (a.getChange().get(2));
          }
        }
      }
      if(currentTime <= s.getDisappears()
              && currentTime >= s.getAppears()
              && s instanceof Rectangle){
        g.setColor(new Color(red, green, blue));
        g.fillRect(x, y, width, height);
        shapes.set(i, new Rectangle(s.getName(), (float)x, (float)y, (float)width, (float)height, red, blue, green, s.getAppears(), s.getDisappears()));
      }
      if(currentTime <= s.getDisappears()
              && currentTime >= s.getAppears()
              && s instanceof Oval) {
        g.setColor(new Color(red, green, blue));
        g.fillOval(x, y, width, height);
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
}
