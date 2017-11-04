package view;

import java.util.Scanner;

import javax.swing.*;

/**
 * This is the view.TextualView class that output all the data from the model in text format in the
 * window.
 */
public class TextualView extends JFrame implements IView {

  private int tickPerSecond;
  String message;

  /**
   * This constructor of the view.TextualView class.
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
    this.message = message;
    text.setText(this.modifyResult(this.message));
    panel.add(text);
    JScrollPane pane = new JScrollPane(panel);
    this.add(pane);
    pack();
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

  /**
   * This is a getter method that translate the
   * @return
   */
  public String toTxt() {
    return this.modifyResult(this.message);
  }
}