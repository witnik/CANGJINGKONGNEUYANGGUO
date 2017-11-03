package view;

import java.util.ArrayList;

import javax.swing.*;

import model.AnimationModel;
import model.IAnimation;
import model.IAnimationModel;
import model.Oval;
import model.Rectangle;
import model.Shape;

public class VisualView extends JFrame implements IView{
  IAnimationModel model = new AnimationModel();
  private JScrollPane pane;
  private JPanel panel;
  private AnimationPanel animationPanel;

  public VisualView(IAnimationModel model){
    super();
    this.setSize(500,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.model = model;
    ArrayList<Shape> shapes = this.model.getShapes();
    ArrayList<IAnimation> animations = this.model.getAnimation();
    animationPanel = new AnimationPanel(shapes, model.getAnimation(), 1);
    animationPanel.draw();
    JScrollPane p = new JScrollPane(animationPanel);
    this.add(p);
    pack();
  }

  public static void main(String[] args) {
    AnimationModel model = new AnimationModel();
    Shape s1 = new Rectangle("r1", 0.0f, 0.0f, 10.0f, 10.0f, 0.1f, 0.1f, 0.3f, 3, 20);
    Shape s2 = new Oval("r2", 10.0f, 50.0f, 3.0f, 6.0f, 0.1f, 0.1f, 0.3f,5,25);
    model.addShape(s1);
    model.addShape(s2);
    model.scale(s2, 20.0f, 10.0f, 6, 10);
    model.move(s1, 50.0f, 60.0f, 5, 19);
    model.changeColor(s1, 0.5f, 0.7f, 0.6f, 6, 9);
    VisualView visual = new VisualView(model);
    visual.makeVisible();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setDisplaySpeed(int tickPerSecond) {

  }

  @Override
  public void showError(String error) {

  }

  @Override
  public void refresh() {

  }
}
