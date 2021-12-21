import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatCardModule } from '@angular/material/card';
import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    MatButtonModule,
    MatCardModule,
    NoopAnimationsModule
  ],
  providers: [
    {
      // Necessary for appropriate injection of the window object into the AppComponent constructor.
      provide: 'windowObject',
      useValue: window
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
