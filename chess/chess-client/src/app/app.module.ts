import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http'
import { RouterModule, Routes } from '@angular/router'

import {MaterialModule} from './material.module';
import { AppComponent } from './app.component';
import { ControlComponent } from './components/control.component';
import { BoardComponent } from './components/board.component';
import {ChessService} from './services/chess.service';
import { MainComponent } from './components/main.component';
import { PlayComponent } from './components/play.component';

const appRoutes: Routes = [
	{ path: '', component: MainComponent },
	{ path: 'game/:gid', component: PlayComponent },
	{ path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    ControlComponent,
    BoardComponent,
    MainComponent,
    PlayComponent
  ],
  imports: [
		BrowserModule, BrowserAnimationsModule,
		FormsModule, ReactiveFormsModule,
	   HttpClientModule, RouterModule.forRoot(appRoutes, { useHash: true }),
		MaterialModule
  ],
  providers: [
	  ChessService,
	  { provide: Window, useValue: window }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
