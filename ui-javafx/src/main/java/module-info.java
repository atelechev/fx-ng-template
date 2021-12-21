module fr.atelechev.fxng {
  requires static lombok;
  requires javafx.controls;
  requires javafx.web;
  requires org.eclipse.jetty.server;
  requires org.eclipse.jetty.servlet;
  requires jdk.jsobject;
  opens fr.atelechev.fxng to javafx.graphics;
  exports fr.atelechev.fxng;
}