# FX-NG Template: JavaFX Application

This module contains the JavaFX part of the FX-NG Template implementation.

## Build & Run

Please check the `README.md` of the parent module for the instructions to build and run the application.

## Implementation Details

The entry point of the JavaFX app is the `fr.atelechev.fxng.App` class, which fills a `Stage` reference with a `WebView`
instance, configured to host an Angular application.

The artifact of this module is packaged as an executable JAR file, which contains all the necessary dependencies. The
Maven Shade plugin is used for this purpose.

The artifact also contains all the files packaged in the `ui-angular` module, which are made accessible as class path
resources via the build sequence.

The JavaFX application uses an instance of the Jetty server in order to serve the static resources containing the
Angular part. The instance of the server listens on a random port in the range between 10K and 30K.

The `AngularAppView` object wraps an instance of a `WebEngine`, which needs some special remarks:

* The browsing history is disabled in order to de-activate the standard back and forward actions, which are usually not
  supported in a traditional standalone app.
* An ad-hoc logic blocks the possibility to open the Angular UI from a Web browser, only the internal `WebView`
  instance is allowed to access it. This is done in order to isolate the app as a standalone one, because opening the UI
  from elsewhere does not make sense in this context. However, this feature might be disabled for development purposes.

The communication between the JavaFX part and the Angular app is done via the "action" objects that implement the
`FxNgAction` interface.

* The signatures of these objects must also be declared in the Angular part, in order to be accessible. Please check the
  doc for the `ui-angular` module for more details.
* Their instance references must be stored in a collection during the life time of the application, in order to prevent
  them from being garbage collected, which would break the link with the Angular part.

Please also check the Javadoc of the objects implemented in the `fr.atelechev.fxng` package for more details.
