import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { WebcamModule } from 'ngx-webcam'
import { HttpClientModule } from '@angular/common/http'
import { RouterModule, Routes } from '@angular/router'

import { AppComponent } from './app.component';
import { CameraComponent } from './components/camera.component';
import { PostComponent } from './components/post.component';
import {CameraService} from './camera.service';

const appRoute: Routes = [
	{ path: '', component: CameraComponent },
	{ path: 'post', component: PostComponent },
]

@NgModule({
  declarations: [
    AppComponent,
    CameraComponent,
    PostComponent
  ],
  imports: [
    BrowserModule, WebcamModule,
	  FormsModule, ReactiveFormsModule,
	  HttpClientModule, RouterModule.forRoot(appRoute)
  ],
  providers: [ CameraService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
