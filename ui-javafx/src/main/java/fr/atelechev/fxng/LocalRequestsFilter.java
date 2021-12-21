package fr.atelechev.fxng;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ResourceHandler;

import java.io.IOException;

/**
 * Used to filter the incoming requests for the static resources of the Angular app.
 */
@Slf4j
public class LocalRequestsFilter extends ResourceHandler {

  private final String userAgentToken;

  public LocalRequestsFilter(String token) {
    this.userAgentToken = token;
    setResourceBase(calculateResourcesBase());
  }

  private String calculateResourcesBase() {
    var pathToResources = LocalRequestsFilter.class.getProtectionDomain()
        .getCodeSource()
        .getLocation()
        .toExternalForm();
    /*
      The resource base values are different for the app launched from a Jar file or from within the IDE.
      - for a jar file, it contains the full path to the jar file and then the path to the resource.
      - for launching from the IDE, it contains the full path to the resource in the local file system.
     */
    var isInsideJar = pathToResources.endsWith(".jar");
    var launchContext = isInsideJar ? "JAR" : "IDE";
    log.debug("Starting from {}, static resources are in: {}", launchContext, pathToResources);
    return isInsideJar ? "jar:%1$s!/".formatted(pathToResources) : pathToResources;
  }

  @Override
  public void handle(String target,
                     Request baseRequest,
                     HttpServletRequest request,
                     HttpServletResponse response) throws IOException, ServletException {
    log.trace("Requested: {}", target);
    var requestUserAgent = request.getHeader(HttpHeader.USER_AGENT.toString());
    /*
      All the requests that do not end with our random token are rejected.
      This prevents opening the UI from a Web browser, only the current application
      will be allowed to show the Angular app.
     */
    if (!requestUserAgent.endsWith(userAgentToken)) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }
    super.handle(target, baseRequest, request, response);
  }

}
