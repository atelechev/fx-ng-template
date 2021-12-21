package fr.atelechev.fxng;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

/**
 * Main class for the JavaFx application.
 * <p>
 * This class can be launched through the IDE in order to test the app in the dev mode.
 */
@Slf4j
public class App extends Application {

  /*
    The name of the folder in the classpath resources, which contains the Angular app bundles.
   */
  protected static final String NG_RESOURCES_DIR = "ui-angular";

  private final EmbeddedJetty server;

  private AngularAppView angularView;

  public App() {
    this.server = new EmbeddedJetty();
  }

  @Override
  public void start(Stage stage) {
    server.start();

    angularView = new AngularAppView(stage, server);
    angularView.loadPage("http://localhost:%1$s/%2$s/index.html".formatted(server.getPort(),
                                                                           NG_RESOURCES_DIR));

    stage.initStyle(StageStyle.UNDECORATED);
    stage.setScene(initScene());
    stage.show();
  }

  private Scene initScene() {
    var vbox = new VBox();
    vbox.getChildren().addAll(angularView.getBrowser());
    return new Scene(vbox, 800, 600);
  }

  public static void main(String[] args) {
    launch();
  }

}