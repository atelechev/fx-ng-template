package fr.atelechev.fxng;

/**
 * Used to launch the application from an executable jar.
 *
 * This class is a known work-around to start a JavaFx application
 * packaged with the Maven Shade plugin.
 */
public class Starter {

  public static void main(String[] args) {
    App.main(args);
  }

}
