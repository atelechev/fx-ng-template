# FX-NG Template: Angular Application

This module contains the Angular part of the FX-NG Template implementation.

It is a very basic Angular application used for demo purposes.

It supports two actions that link the Angular UI with the JavaFX part:

* toggle the view between the windowed and the full screen modes.
* close the entire app by clicking a button.

The linking between the Angular and the JavaFX parts are done through:

* Enhancing the `global.Window` object with references to the actions supported on the JavaFX side, see
  the `app.component.ts` file.
* Making the `window` object available for injection in the `AppModule` config.
* The `baseHref` value in the `angular.json` file must be set to the name of the folder that is expected to contain the
  bundles of the app. This value must be the same with the `App.NG_RESOURCES_DIR` value in the JavaFx part.

This application requires [Node 16+ and Npm](https://nodejs.org) to be installed on the dev host.

To start the app in the dev mode, run `npm start` (`npm install` also needs to be executed once before the first launch)
. The app will be available at `localhost:4200`.

Please note that when launched in the dev mode and open from a Web browser, the JavaFx part of the app will not be
available and the actions triggering will produce errors.
