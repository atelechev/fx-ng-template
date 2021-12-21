package fr.atelechev.fxng;

import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles the toggling of the UI view from the windowed to the full-screen mode.
 */
@Slf4j
public class ToggleFullScreenAction implements FxNgAction {

  private final Stage stage;

  private final WebView browser;

  @Getter
  private boolean fullScreen;

  public ToggleFullScreenAction(Stage stage, WebView browser) {
    this.stage = stage;
    this.browser = browser;
    this.fullScreen = false;
  }

  @Override
  public String getKey() {
    return "actionFullScreen";
  }

  @Override
  public void execute() {
    fullScreen = !fullScreen;
    stage.setFullScreen(fullScreen);
    browser.resize(stage.getWidth(), stage.getHeight());
    log.debug("Showing {}.", fullScreen ? "full screen" : "windowed");
  }

}
