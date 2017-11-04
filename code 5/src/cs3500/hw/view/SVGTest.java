import org.junit.Test;

import static org.junit.Assert.assertEquals;

import cs3500.hw.model.AnimationModel;
import cs3500.hw.model.IAnimationModel;
import cs3500.hw.model.Oval;
import cs3500.hw.model.Rectangle;
import cs3500.hw.model.Shape;
import cs3500.hw.view.SVGView;

/**
 * This is the testing class that test the SVGView class.
 */
public class SVGTest {
  private Shape rectangle = new Rectangle("r", 10.0f,10.0f,5.0f,
          6.0f,3.0f, 1.9f,2.3f,10,40);
  private Shape oval = new Oval("o", 30.0f,50.0f,2.0f,3.0f,
          1.0f,2.3f,5.6f,7,220);
  private SVGView svg;
  private IAnimationModel model = new AnimationModel();

  /**
   * This is the test that checks whether SVGView returns the right svg output.
   */
  @Test
  public void view() {
    model.addShape(rectangle);
    model.addShape(oval);
    model.move(rectangle,12.0f,43.0f,10,15);
    model.move(oval, 50.0f,250.0f,200,220);
    model.scale(rectangle,12.0f,50.0f,12,20);
    model.changeColor(oval,2.1f,2.2f,2.3f,11,21);
    model.scale(oval,30.2f,13.0f,8,17);
    model.animate();
    svg = new SVGView(model, 20);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
            "    xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<ellipse id=\"o\" cx=\"50\" cy=\"250\" rx=\"30\" ry=\"13\" " +
            "fill=\"rgb(536,587,561)\" visibility=\"hidden\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" " +
            "to=\"visible\" begin=\"350ms\"/>\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" " +
            "to=\"hidden\" begin=\"11000ms\"/>\n" +
            "<animate attributeType=\"XML\" attributeName=\"rx\" from=\"30\" to=\"30\"\n" +
            " dur=\"0ms\" begin=\"400ms\" repeatCount=\"0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"XML\" attributeName=\"ry\" from=\"13\" to=\"13\"\n" +
            " dur=\"450ms\" begin=\"400ms\" repeatCount=\"0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"XML\" attributeName=\"fill\" from=\"rgb(536,587,561)\" " +
            "to=\"rgb(536,587,561)\"\n" +
            " dur=\"500ms\" begin=\"550ms\" repeatCount=\"0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"10000ms\"" +
            " dur=\"1000ms\" attributeName=\"cx\" " +
            "from=\"50\" to=\"50\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"1000ms\" " +
            "attributeName=\"cy\" from=\"250\" to=\"250\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "<rect id=\"r\" x=\"12\" y=\"43\" width=\"12\" height=\"50\" " +
            "fill=\"rgb(765,485,587)\" visibility=\"hidden\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" " +
            "to=\"visible\" begin=\"500ms\"/>\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" " +
            "to=\"hidden\" begin=\"2000ms\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"250ms\" " +
            "attributeName=\"x\" from=\"12\" to=\"12\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"250ms\" " +
            "attributeName=\"y\" from=\"43\" to=\"43\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"XML\" attributeName=\"width\" from=\"12\" to=\"12\"\n" +
            " dur=\"0ms\" begin=\"600ms\" repeatCount=\"0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"XML\" attributeName=\"height\" from=\"50\" to=\"50\"\n" +
            " dur=\"400ms\" begin=\"600ms\" repeatCount=\"0\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "</svg>",svg.getSVG());
  }

  /**
   * This is the test that checks whether the SVGView outputs the correct svg output when the
   * model is empty.
   */
  @Test
  public void testEmptySvg() {
    svg = new SVGView(model,1);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
            "    xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "</svg>",svg.getSVG());
  }
}


