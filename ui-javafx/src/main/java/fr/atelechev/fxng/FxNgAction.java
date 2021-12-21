package fr.atelechev.fxng;

/**
 * Used to normalize the handling of the actions triggered
 * from the Angular part of the app.
 */
public interface FxNgAction {

  /**
   * The key identifying the action of the implementor.
   *
   * Must be unique among all existing actions.
   *
   * The action is used from the Angular app with the window.{key} reference,
   * where {key} is the string returned from this method.
   *
   * @return String the key identifying this action.
   */
  String getKey();

  /**
   * Executes the logic of this action.
   *
   * This method should be called from the Angular app with window.{key}.execute().
   */
  void execute();

}
