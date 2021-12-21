package fr.atelechev.fxng;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;

import java.util.Collection;
import java.util.List;

/**
 * Wraps instances of a {@code WebView} and {@code WebEngine} objects,
 * configured to host the UI in Angular.
 */
@Slf4j
public class AngularAppView {

  @Getter
  private final WebView browser;

  @Getter
  private final WebEngine webEngine;

  /*
    Contains all action objects with messages to be transmitted between the
    Angular app instance and JavaFx.

    These actions need to remain in a dedicated collection during the lifetime of the app,
    because otherwise their references are collected by the GC, making them unavailable.
   */
  private final Collection<FxNgAction> actions;

  public AngularAppView(Stage stage, EmbeddedJetty server) {
    this.browser = new WebView();
    this.actions = List.of(new CloseAppAction(server),
                           new ToggleFullScreenAction(stage, browser));
    this.webEngine = initWebEngine(server.getUserAgentToken());
  }

  public void loadPage(String url) {
    webEngine.load(url);
  }

  private WebEngine initWebEngine(String userAgentToken) {
    var webEngine = browser.getEngine();
    // Disables the Web browsing history: back-forward actions will not be available in the UI
    webEngine.getHistory().setMaxSize(0);
    /*
     Append the token to the user agent: allows to identify the requests
     coming from the JavaFx embedded browser and reject any other requests.
     */
    var userAgent = "%1$s %2$s".formatted(webEngine.getUserAgent(), userAgentToken);
    webEngine.setUserAgent(userAgent);
    log.trace("User Agent: {}", webEngine.getUserAgent());
    bindActions(webEngine);
    return webEngine;
  }

  private void bindActions(WebEngine webEngine) {
    var window = (JSObject) webEngine.executeScript("window");
    // All action references are pushed into the {@code window} object of the JS app.
    actions.forEach(action -> window.setMember(action.getKey(), action));
  }

}
