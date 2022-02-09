import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { AddComponent } from './components/add.component';
import { UpdateComponent } from './components/update.component';
import { TodoComponent } from './components/todo.component';
import {TodoService} from './todo.service';

const appRoutes: Routes = [ 
	{ path: '', component: MainComponent },
	{ path: 'todo/:tid', component: UpdateComponent },
	{ path: 'add', component: AddComponent },
	{ path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent, AddComponent, UpdateComponent, TodoComponent
  ],
  imports: [
    BrowserModule, 
	  FormsModule, ReactiveFormsModule,
	  RouterModule.forRoot(appRoutes)
  ],
  providers: [ TodoService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
