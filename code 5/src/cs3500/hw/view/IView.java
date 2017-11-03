package view;

public interface IView {
  /**
   * Make the view visible. this should be called after a view is constructed.
   */
  void makeVisible();

  /**
   * Show error message.
   *
   * @param error the error message to be displayed.
   */
  void showError(String error);
}