import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { OneComponent } from './components/one.component';
import { NumberComponent } from './components/number.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

const appRoutes: Routes = [
  { path: '', component: MainComponent },
  { path: 'one', component: OneComponent },
  { path: 'number/:num', component: NumberComponent },
  { path: '**', redirectTo: '/one', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent, OneComponent, NumberComponent
  ],
  imports: [
    BrowserModule,
    FormsModule, ReactiveFormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
