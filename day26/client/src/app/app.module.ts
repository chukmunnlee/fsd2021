import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { CustomerComponent } from './components/customer.component';
import { CustomerService } from './customer.service';

@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent
  ],
  imports: [
    BrowserModule, HttpClientModule
  ],
  providers: [ CustomerService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
