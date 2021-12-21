import { Component, Inject } from '@angular/core';
import { Action } from 'src/models/action';
import { ToggleFullScreenAction } from 'src/models/toggle-fullscreen-action';

/**
 * The global window object is enhanced with the references
 * declared in this interface.
 *
 * The names of the interface members correspond to the keys
 * returned by the getKey() implementations in the Java part.
 */
declare global {
  interface Window {
    actionClose: Action;
    actionFullScreen: ToggleFullScreenAction;
  }
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(@Inject('windowObject') private readonly _window: Window) {

  }

  public get toggleFullScreenLabel(): string {
    return this._window.actionFullScreen?.isFullScreen() ? 'WINDOWED' : 'FULL SCREEN';
  }

  public toggleFullScreen(): void {
    this._window.actionFullScreen.execute();
  }

  public closeApp(): void {
    this._window.actionClose.execute();
  }

}
