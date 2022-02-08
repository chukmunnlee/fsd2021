import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { CityListComponent } from './components/city-list.component';
import { WeatherComponent } from './components/weather.component';
import { WeatherService } from './weather.service';

const appRoutes: Routes = [
  { path: '', component: CityListComponent },
  { path: 'weather/:city', component: WeatherComponent },
]

@NgModule({
  declarations: [
    AppComponent,
    CityListComponent, WeatherComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ WeatherService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
