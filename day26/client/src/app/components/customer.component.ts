import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { CustomerService } from '../customer.service';
import { Customer } from '../models';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  customer!: Customer

  constructor(private customerSvc: CustomerService) { }

  ngOnInit(): void {
    this.customerSvc.getCustomer()
      .then(result => {
        this.customer = result
      })
  }

}
