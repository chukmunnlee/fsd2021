import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http'
import { RouterModule, Routes } from '@angular/router'

import { AppComponent } from './app.component';
import { ListComponent } from './components/list.component';
import { AddComponent } from './components/add.component';
import { TaskService } from './task.service';

const appRoutes: Routes = [
	{ path: '', component: ListComponent },
	{ path: 'add', component: AddComponent },
	{ path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    ListComponent, AddComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule,
	  HttpClientModule, RouterModule.forRoot(appRoutes)

  ],
  providers: [ TaskService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
