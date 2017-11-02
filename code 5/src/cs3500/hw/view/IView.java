package view;

public interface IView {
  /**
   * Make the view visible.
   * this should be called after a view is constructed.
   */
  void makeVisible();

  /**
   * set running speed of this anime.
   * @param tickPerSecond   speed of the anime.
   */
  void setDisplaySpeed(int tickPerSecond);

  /**
   * show error message.
   * @param error   the error message to be displayed.
   */
  void showError(String error);

  /**
   * Signal the view to draw itself
   */
  void refresh();

}
