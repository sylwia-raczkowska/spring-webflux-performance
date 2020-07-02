import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LaunchesComponent } from './launch/launches/launches.component';
import { LaunchDetailComponent } from './launch/launch-detail/launch-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    LaunchesComponent,
    LaunchDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
