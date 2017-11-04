package view;

import org.junit.Test;
import org.junit.runner.RunWith;

import cs3500.hw05.model.AnimationModel;
import cs3500.hw05.model.IAnimationModel;
import cs3500.hw05.model.Oval;
import cs3500.hw05.model.Rectangle;
import cs3500.hw05.model.Shape;

import static org.junit.Assert.*;

public class TextualViewTest {
  private Shape rectangle = new Rectangle("r", 10.0f,10.0f,5.0f,
          6.0f,3.0f, 1.9f,2.3f,10,40);
  private Shape oval = new Oval("o", 30.0f,50.0f,2.0f,3.0f,
          1.0f,2.3f,5.6f,7,220);
  private TextualView text;
  private IAnimationModel model = new AnimationModel();

  @Test
  public void emptyTextView() {
    text = new TextualView(model.toString(), 2);
    assertEquals("<html>Shapes:<br></html>", text.toTxt());
  }

  @Test
  public void textViewWithoutAnime() {
    model.addShape(oval);
    model.addShape(rectangle);
    model.animate();
    text = new TextualView(model.toString(), 1);
    assertEquals("<html>Shapes:<br>Name: o<br>Type: oval<br>" +
            "Lower-left corner: (30.0,50.0), xRadius: 2.0, yRadius: 3.0, Color: (1.0,5.6,2.3)<br>" +
            "Appears at t=7.0s<br>Disappears at t=220.0s<br><br><br>Name: r<br>" +
            "Type: rectangle<br>" +
            "Lower-left corner: (10.0,10.0), Width: 5.0, Height: 6.0, Color: (3.0,2.3,1.9)<br>" +
            "Appears at t=10.0s<br>Disappears at t=40.0s<br><br><br></html>",text.toTxt());
  }

  @Test
  public void testViewWithAnime() {
    model.addShape(rectangle);
    model.addShape(oval);
    model.move(rectangle,12.0f,43.0f,10,15);
    model.move(oval, 50.0f,250.0f,200,220);
    model.scale(rectangle,12.0f,50.0f,12,20);
    model.changeColor(oval,2.1f,2.2f,2.3f,11,21);
    model.scale(oval,30.2f,13.0f,8,17);
    model.animate();
    text = new TextualView(model.toString(), 1);
    assertEquals("<html>Shapes:<br>" +
            "Name: o<br>Type: oval<br>" +
            "Lower-left corner: (30.0,50.0), xRadius: 2.0, yRadius: 3.0, Color: (1.0,5.6,2.3)<br>" +
            "Appears at t=7.0s<br>Disappears at t=220.0s<br><" +
            "br>" +
            "<br>Name: r<br>" +
            "Type: rectangle<br>" +
            "Lower-left corner: (10.0,10.0), Width: 5.0, Height: 6.0, Color: (3.0,2.3,1.9)<br>" +
            "Appears at t=10.0s<br>Disappears at t=40.0s<br>" +
            "<br>" +
            "<br>Shape o scales from Width: 2.0, Height: 3.0 to Width: 30.2," +
            " Height: 13.0 from t=8.0s to t=17.0s<br>" +
            "Shape r moves from (10.0,10.0) to (12.0,43.0) from time t=10.0s to t=15.0s<br>" +
            "Shape o changes color from (1.0,5.6,2.3) to (2.1,2.2,2.3) from t=11.0s to t=21.0s<br>" +
            "Shape r scales from Width: 5.0, Height: 6.0 to " +
            "Width: 12.0, Height: 50.0 from t=12.0s to t=20.0s<br>" +
            "Shape o moves from (30.0,50.0) to (50.0,250.0) from time t=200.0s to t=220.0s<br>" +
            "</html>", text.toTxt());
  }
}

