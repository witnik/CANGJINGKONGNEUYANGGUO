import sun.security.provider.SHA;

import java.util.ArrayList;

import javax.swing.*;

import cs3500.hw05.model.AnimationModel;
import cs3500.hw05.model.IAnimation;
import cs3500.hw05.model.Shape;

public class SVGView extends JFrame implements IView {
  AnimationPanel panel;

  SVGView(AnimationPanel panel){

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
