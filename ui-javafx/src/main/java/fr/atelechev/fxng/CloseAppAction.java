package fr.atelechev.fxng;

import javafx.application.Platform;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles the closing of the application after a corresponding message
 * comes from the Angular app.
 */
@Slf4j
public class CloseAppAction implements FxNgAction {

  private final EmbeddedJetty embeddedJetty;

  public CloseAppAction(EmbeddedJetty jetty) {
    this.embeddedJetty = jetty;
  }

  @Override
  public String getKey() {
    return "actionClose";
  }

  @Override
  public void execute() {
    log.debug("App close requested via NG");
    embeddedJetty.stop();
    Platform.exit();
    System.exit(0);
  }

}
