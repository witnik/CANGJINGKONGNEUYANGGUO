package view;

import sun.awt.SunHints;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;

/**
 * This is the TextualView class that output all the data from the model in text format in the
 * window.
 */
public class TextualView extends JFrame implements IView {

  private int tickPerSecond;

  /**
   * This constructor of the TextualView class.
   *
   * @param message the String message output from the model
   */
  public TextualView(String message, int tickPerSecond) {
    super();
    this.tickPerSecond = tickPerSecond;
    this.setSize(500, 500);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    JLabel text = new JLabel();
    text.setText(this.modifyResult(message));
    panel.add(text);
    JScrollPane pane = new JScrollPane(panel);
    this.add(pane);
    pack();
  }

  public static void main(String[] args) {
    String s = "Shapes:\n" +
            "Name: R\n" +
            "Type: rectangle\n" +
            "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" +
            "Appears at t=1\n" +
            "Disappears at t=100\n" +
            "\n" +
            "Name: C\n" +
            "Type: oval\n" +
            "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n" +
            "Appears at t=6\n" +
            "Disappears at t=100\n" +
            "\n" +
            "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n" +
            "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n" +
            "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50 to t=80\n" +
            "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70\n" +
            "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100";
    TextualView view = new TextualView(s, 1);
    view.makeVisible();
  }

  /**
   * Make the view visible. this should be called after a view is constructed.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Show error message.
   *
   * @param error the error message to be displayed.
   */
  @Override
  public void showError(String error) {
    JOptionPane.showMessageDialog(this, error,
            "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * This is the method that modify the string output into the required format.
   *
   * @param str the string object that are being outputed
   * @return the formatted string object that are being outputed
   */
  private String modifyResult(String str) {
    String[] all = str.split("t=");
    for (int i = 0; i < all.length; i++) {
      String s = all[i];
      int length = 0;
      while (length < s.length() && Character.isDigit(s.charAt(length))) {
        length += 1;
      }
      if (length != 0) {
        int time = Integer.parseInt(s.substring(0, length));
        double replace = (double) time / tickPerSecond;
        String rest = s.substring(length, s.length());
        all[i] = String.format("t=%.1fs", replace) + rest;
      }
    }
    String result = "";
    for (String str2 : all) {
      result += str2;
    }
    Scanner scan = new Scanner(result);
    String st = "";
    while (scan.hasNextLine()) {
      String line = scan.nextLine();
      st += line + "<br>";
    }

    return "<html>" + st + "</html>";
  }

}
