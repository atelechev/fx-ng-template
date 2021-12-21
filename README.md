# FX-NG Template

## Overview

This project is a POC/prototype of a standalone Java application that uses Angular as the framework for the UI part.

The main motivation for this experiment was to be able to use the power and flexibility of an Angular application in
order to render a UI in the standalone/desktop mode, without the need to create too much JavaFX-related code.

The idea consists in the following:

* use JavaFX as the basis for a standalone Java application.
* implement all the visual UI features with Angular, which communicates with the JavaFX part through JavaScript objects.

The JavaFX part contains:

* a standard JavaFX application, with only a `WebView` instance allowing to render an SPA written in Angular.
* an embedded instance of Jetty, which serves the static resources of the Angular app.

The Angular part contains a standard Angular application. Its only special elements are action interfaces that match the
actions supported in the JavaFX part and are used to pass messages into the latter.

This project can be used for further experiments with the concept, or as a template for a real standalone app.

## Project Structure

The `ui-angular` module contains the implementation of a simple Angular app, serving as example.

The `ui-javafx` module contains the implementation of the JavaFX part.

For more details, please check the respective `README`s in these modules.

## Build

This project requires Java 15+ and Maven 3.8+ to be installed. Node and NPM are also recommended for the Angular part.

To build the application, run `mvn clean install` in the root folder of the project, either in the command line or
through your IDE (IntelliJ was used for the development).

## Run

To run the application from an executable jar, after building the application, use the command

    java -jar ui-javafx/target/ui-javafx-0.0.1-SNAPSHOT.jar

To run from the IDE, execute the `fr.atelechev.fxng.App.main` method through the standard UI of the IDE.

## License

This software is distributed under MIT license conditions.

Please check more details in `LICENSE` file.

--- 

(c) 2021 Anton Telechev 
