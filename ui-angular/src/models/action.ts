/**
 * Represents an action that is triggered in the JavaFx part of the application
 * by calling the execute() method.
 *
 * This interface corresponds to the FxNgAction interface in the Java code of the app,
 * but it does not contain the getKey() method, because it is not used on the Angular side.
 */
export interface Action {
  execute(): void;
}
