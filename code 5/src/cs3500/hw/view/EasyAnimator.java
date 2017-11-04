package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import cs3500.hw05.model.AnimationModel;

/**
 * this class represent an animator, which is used to run an animation.
 * main method will take in filename, viewType, tickpersecond and outputFile.
 * if tickPersecond or outputFile is not found, formerone will be set to 1 and, output will be set to System.out.
 * throw exception if viewType or filename is not specified or valid.
 */
public final class EasyAnimator {

  public static void main(String[] args) {
    // FILL IN HERE
    String fileName="";
    String viewType="";
    String outPutFile="System.out";
    int tickPersecond =1;
    IView view = new TextualView("",1);

    if (args.length%2 !=0) {view.showError("command length has to be even");
    System.exit(-1);}

    for (int i=0;i<args.length;i+=2) {
      String s = args[i] + " "+args[i+1];
      Scanner scan = new Scanner(s);
      String cm = scan.next();
      switch (cm) {
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
          if (scan.hasNext()) {
            int temp = Integer.parseInt(scan.next());
            System.out.print("temp is: " + temp);
            if (temp < 1) {
              view.showError("tick per second must be positive");
              throw new IllegalArgumentException("tick per second must be positive");
            }
            else {
            tickPersecond = temp;
          }}
          else {
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
        PrintWriter writer;
        if (outPutFile.equalsIgnoreCase("System.out")){
        writer = new PrintWriter(System.out);
        }
        else {
          writer = new PrintWriter(outPutFile);
        }
        writer.println(message);
        writer.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    else if (viewType.compareTo("visual")==0) {
      System.out.print(tickPersecond);
      view = new VisualView(model, tickPersecond);
      view.makeVisible();
    }
    else {
      view.showError("Input view Type is invalid");
      System.exit(-1);
    }
  }
}
