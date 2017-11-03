package view;

import sun.security.provider.SHA;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import model.AnimationModel;
import model.IAnimation;
import model.IAnimationModel;
import model.Oval;
import model.Rectangle;
import model.Shape;

public class SVGView extends JFrame implements IView {
  IAnimationModel model;
  AnimationPanel panel;

  SVGView(AnimationModel model, int tickPerSecond){
    super();
    this.setSize(500,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.model = model;
    ArrayList<Shape> shapes = this.model.getShapes();
    ArrayList<IAnimation> animations = this.model.getAnimation();
    panel = new AnimationPanel(shapes, animations, tickPerSecond);

    JLabel text = new JLabel();
    text.setText(panel.toSVG());
    this.panel.add(text);

    JScrollPane p = new JScrollPane(panel);
    this.add(p);
    pack();
    System.out.print(panel.toSVG());
  }

  public static void main(String[] args) {
    AnimationModel model = new AnimationModel();
    Shape s1 = new Rectangle("r1", 0.0f, 0.0f, 10.0f, 10.0f, 0.1f, 0.1f, 0.3f, 3, 20);
    Shape s2 = new Oval("r2", 10.0f, 50.0f, 3.0f, 6.0f, 0.1f, 0.1f, 0.3f,3,25);
    model.addShape(s1);
    model.addShape(s2);
    model.scale(s2, 20.0f, 10.0f, 6, 10);
    model.move(s2, 10.0f, 100.0f, 6, 11);
    model.move(s1, 50.0f, 60.0f, 5, 19);
    model.changeColor(s1, 0.5f, 0.7f, 0.6f, 6, 8);
    SVGView svg = new SVGView(model, 1);
    svg.makeVisible();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void showError(String error) {

  }

  @Override
  public void refresh() {
    this.repaint();
  }
}