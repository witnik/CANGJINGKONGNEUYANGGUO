import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;


import javax.swing.*;

import cs3500.hw05.model.AnimationModel;
import cs3500.hw05.model.IAnimation;
import cs3500.hw05.model.IAnimationModel;
import cs3500.hw05.model.Oval;
import cs3500.hw05.model.Rectangle;
import cs3500.hw05.model.Shape;

public class VisualView extends JFrame implements IView{
  IAnimationModel model = new AnimationModel();
  private JScrollPane pane;
  private JPanel panel;
  private AnimationPanel animationPanel;

  public VisualView(IAnimationModel model, int tickPerSecond){
    super();
    this.setSize(500,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.model = model;
    ArrayList<Shape> shapes = this.model.getShapes();
    ArrayList<IAnimation> animations = this.model.getAnimation();
    animationPanel = new AnimationPanel(shapes, model.getAnimation(), tickPerSecond);

    JScrollPane p = new JScrollPane(animationPanel);
    animationPanel.draw();
    this.add(p);
    pack();
  }

  public static void main(String[] args) {
    AnimationModel model = new AnimationModel();
    Shape s1 = new Rectangle("r1", 0.0f, 0.0f, 10.0f, 10.0f, 0.1f, 0.1f, 0.3f, 3, 20);
    Shape s2 = new Oval("r2", 10.0f, 50.0f, 3.0f, 6.0f, 0.1f, 0.1f, 0.3f,3,25);
    model.addShape(s1);
    model.addShape(s2);
    model.scale(s2, 20.0f, 10.0f, 6, 10);
    model.move(s1, 50.0f, 60.0f, 5, 19);
    model.changeColor(s1, 0.5f, 0.7f, 0.6f, 6, 8);
    VisualView visual = new VisualView(model, 1);
    visual.makeVisible();
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

  }
}