package view;

import java.util.ArrayList;

import javax.swing.*;

import model.AnimationModel;
import model.IAnimation;
import model.IAnimationModel;
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
    Shape s1 = new Rectangle("r1", 20.0f, 20.0f, 10.0f, 10.0f, 8.0f, 105.0f, 126.0f, 3, 20);
    Shape s2 = new Rectangle("r2", 10.0f, 50.0f, 30.0f, 60.0f, 8.0f, 105.0f, 126.0f,5,30);
    model.addShape(s1);
    model.addShape(s2);
    model.scale(s2, 50.0f, 100.0f, 8, 12);
    model.move(s1, 50.0f, 60.0f, 5, 12);
    model.animate();
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
