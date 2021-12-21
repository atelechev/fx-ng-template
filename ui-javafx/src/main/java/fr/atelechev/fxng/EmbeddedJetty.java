package fr.atelechev.fxng;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Wraps an embedded instance of the Jetty server,
 * which serves static resources for the Angular app.
 */
@Slf4j
public class EmbeddedJetty {

  /*
    A random token appended to the user-agent header,
    allowing to filter out requests from outer sources.
   */
  @Getter
  private final String userAgentToken;

  /**
   * The current instance of the embedded Jetty server.
   */
  private final Server server;

  /**
   * The current port on which Jetty is listening.
   */
  @Getter
  private int port;

  public EmbeddedJetty() {
    this.userAgentToken = generateToken();
    this.server = initJettyServer();
    this.port = getRandomPort();
  }

  public void start() {
    var server = initJettyServer();
    /*
      The first available random port between 10K and 30K will be used
      by the embedded Jetty instance.
     */
    while (!attemptStartOnCurrentPort(server)) {
      port = getRandomPort();
    }
    log.debug("Jetty started on port {}", port);
  }

  public void stop() {
    try {
      server.stop();
      log.debug("Jetty stopped");
    } catch (Exception ex) {
      log.warn("Failed to stop the embedded Jetty instance: {}", ex.getMessage(), ex);
    }
  }

  private String generateToken() {
    /*
      The "token" is just a random UUID without dashes.
      It is sufficient to uniquely identify the instance.
     */
    return UUID.randomUUID()
        .toString()
        .replaceAll("-", "")
        .toLowerCase();
  }

  private Server initJettyServer() {
    var server = new Server();
    server.setConnectors(new Connector[]{new ServerConnector(server)});
    server.setHandler(getResourceHandlers());
    return server;
  }

  private HandlerList getResourceHandlers() {
    var handlerList = new HandlerList();
    handlerList.setHandlers(new Handler[]{
        new LocalRequestsFilter(userAgentToken),
        new DefaultHandler()}
    );
    return handlerList;
  }

  private boolean attemptStartOnCurrentPort(Server server) {
    try {
      ((ServerConnector) server.getConnectors()[0]).setPort(port);
      server.start();
      return true;
    } catch (Exception ex) {
      log.warn("Port {} not available: {}", port, ex.getMessage());
      return false;
    }
  }

  private static int getRandomPort() {
    return ThreadLocalRandom.current().nextInt(10_000, 30_000);
  }

}
