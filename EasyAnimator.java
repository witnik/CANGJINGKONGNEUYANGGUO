import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    String fileName= "/Users/TomPeng/Desktop/code-20/buildings.txt";
    String viewType="text";
    String outPutFile="/Users/TomPeng/Desktop/code-20/name.svg";
    //String outPutFile="System.out";
    int tickPersecond =10;

    for (int i=0; i<args.length;i++) {
      String cmd = args[i];
      Scanner scan = new Scanner(cmd);
      switch(scan.next()) {
        case "-if":
          if (scan.hasNext()) {fileName = scan.next();}
          else {throw new IllegalArgumentException("file name not follow -if");}
          break;
        case "-iv":
        if (scan.hasNext()) {viewType = scan.next();}
        else {throw new IllegalArgumentException("vie type is not entered");}
        break;
        case "-o":
          if (scan.hasNext()) {outPutFile = scan.next();}
          break;
        case "-speed":
          if (scan.hasNextInt()) {
            int temp = scan.nextInt();
            if (temp<1) {throw  new IllegalArgumentException("tick per second must be positive");}
            tickPersecond = scan.nextInt();
          }
          else {throw new IllegalArgumentException("tick per second is not entered");}
          break;
        default:
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
    IView view;
    if (viewType.compareTo("text")==0) {
      model.animate();
      view = new TextualView(tickPersecond, model.toString());
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
      throw new IllegalArgumentException("unsupported view Type");
    }
  }
}
