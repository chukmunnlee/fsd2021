import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms'
import { RouterModule, Routes } from '@angular/router'
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { AddComponent } from './components/add.component';
import {PurchaseOrderService} from './purchase-order.service';

const appRoutes: Routes = [
	{ path: '', component: MainComponent },
	{ path: 'add', component: AddComponent },
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent, AddComponent
  ],
	imports: [
		BrowserModule, HttpClientModule,
		FormsModule, ReactiveFormsModule,
		RouterModule.forRoot(appRoutes)
  ],
  providers: [ PurchaseOrderService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
