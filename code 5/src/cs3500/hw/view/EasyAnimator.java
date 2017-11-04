package view;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.swing.*;

import model.AnimationModel;

public final class EasyAnimator {

  public static void main(String[] args) {
    // FILL IN HERE
    String fileName= "/Users/david.li/Downloads/code/toh-8.txt";
    String viewType="visual";
    String outPutFile="/Users/david.li/Downloads/name.svg";
    int tickPersecond = 1000;
    IView view = new TextualView("",1);

    for (String cmd : args) {
      Scanner scan = new Scanner(cmd);
      switch (scan.next()) {
        case "-if":
          if (scan.hasNext()) {
            fileName = scan.next();
          } else {
            view.showError("file name not follow -if");
            throw new IllegalArgumentException("file name not follow -if");
          }
          break;
        case "-iv":
          if (scan.hasNext()) {
            viewType = scan.next();
          } else {
            view.showError("view type is not entered");
            throw new IllegalArgumentException("view type is not entered");
          }
          break;
        case "-o":
          if (scan.hasNext()) {
            outPutFile = scan.next();
          }
          break;
        case "-speed":
          if (scan.hasNextInt()) {
            int temp = scan.nextInt();
            if (temp < 1) {
              view.showError("tick per second must be positive");
              throw new IllegalArgumentException("tick per second must be positive");
            }
            tickPersecond = scan.nextInt();
          } else {
            view.showError("tick per second is not entered");
            throw new IllegalArgumentException("tick per second is not entered");
          }
          break;
        default:
          view.showError("Unidentified attribute");
          throw new IllegalArgumentException("Unidentified attribute");
      }
    }
    AnimationFileReader fileReader = new AnimationFileReader();
    AnimationModel model = null;
    AnimeModelBuilder builder = new AnimeModelBuilder();
    try {
      model = fileReader.readFile(fileName, builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    if (viewType.compareTo("text")==0) {
      model.animate();
      view = new TextualView(model.toString(), tickPersecond);
      view.makeVisible();
    }
    else if (viewType.compareTo("svg")==0) {
      view = new SVGView(model, tickPersecond);
      String message = ((SVGView)view).getSVG();
      view.makeVisible();
      try {
        PrintWriter writer = new PrintWriter(outPutFile);
        writer.println(message);
        writer.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    else if (viewType.compareTo("visual")==0) {
      view = new VisualView(model, tickPersecond);
      view.makeVisible();
    }
    else {
      view.showError("Input is invalid");
    }
  }
}