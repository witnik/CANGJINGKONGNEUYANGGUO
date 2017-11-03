import java.awt.Color;

import cs3500.hw05.model.AnimationModel;
import cs3500.hw05.model.Oval;
import cs3500.hw05.model.Rectangle;
import cs3500.hw05.model.Shape;

public class AnimeModelBuilder implements TweenModelBuilder<AnimationModel> {
  AnimationModel model;
  AnimeModelBuilder() {
    this.model = new AnimationModel();
  }
  AnimeModelBuilder(AnimationModel model) {this.model = model;}
  @Override
  public TweenModelBuilder<AnimationModel> addOval(String name, float cx, float cy, float xRadius, float yRadius, float red, float green, float blue, int startOfLife, int endOfLife) {
    Oval shape = new Oval(name, cx, cy, xRadius, yRadius, red, green, blue, startOfLife, endOfLife);
    this.model.addShape(shape);
    return this;
  }

  @Override
  public TweenModelBuilder<AnimationModel> addRectangle(String name, float lx, float ly, float width, float height, float red, float green, float blue, int startOfLife, int endOfLife) {
    Rectangle shape = new Rectangle(name, lx, ly, width, height, red, green, blue, startOfLife, endOfLife);
    this.model.addShape(shape);
    return this;
  }

  @Override
  public TweenModelBuilder<AnimationModel> addMove(String name, float moveFromX, float moveFromY, float moveToX, float moveToY, int startTime, int endTime) {
 //   Shape s = this.model.getShape(name);
    this.model.move(name, moveToX, moveToY, startTime, endTime);
    return this;
  }

  @Override
  public TweenModelBuilder<AnimationModel> addColorChange(String name, float oldR, float oldG, float oldB, float newR, float newG, float newB, int startTime, int endTime) {

    this.model.changeColor(name, newR, newB, newG, startTime, endTime);
    return this;
  }

  @Override
  public TweenModelBuilder<AnimationModel> addScaleToChange(String name, float fromSx, float fromSy, float toSx, float toSy, int startTime, int endTime) {
    //Shape s = this.model.getShape(name);
    this.model.scale(name, toSx, toSy, startTime, endTime);
    return this;
  }

  @Override
  public AnimationModel build() {
    return this.model;
  }
}
