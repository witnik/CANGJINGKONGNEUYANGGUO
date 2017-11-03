import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import cs3500.hw05.model.AnimationModel;

public final class EasyAnimator {

  public static void main(String[] args) {
    // FILL IN HERE
    String fileName="";
    String viewType="";
    String outPutFile="";
    int tickPersecond =1;

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
      view = new TextualView(tickPersecond, model.toString());
      view.makeVisible();
    }
    if (viewType.compareTo("svg")==0) {
      view = new SVGView(model, tickPersecond);
      view.makeVisible();
      try {
        PrintWriter writer = new PrintWriter(outPutFile);
        writer.write(((SVGView)view).getSVG());
        writer.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    if (viewType.compareTo("visual")==0) {
      view = new VisualView(model, tickPersecond);
      view.makeVisible();
    }
    else {
      throw new IllegalArgumentException("unsupported view Type");
    }
  }
}
