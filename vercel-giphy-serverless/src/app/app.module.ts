import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http'
import { RouterModule, Routes } from '@angular/router'

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { GiphyComponent } from './components/giphy.component';
import {GiphyService} from './giphy.service';

const appRoutes: Routes = [
	{ path: '', component: MainComponent },
	{ path: 'giphy', component: GiphyComponent },
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent, GiphyComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule,
	  HttpClientModule, RouterModule.forRoot(appRoutes)
  ],
  providers: [ GiphyService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
