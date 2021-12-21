import { Action } from './action';

/**
 * Provides the access to the state of the underlying JavaFx window,
 * whether it is in the full screen or windowed mode.
 */
export interface ToggleFullScreenAction extends Action {
  isFullScreen(): boolean;
}
