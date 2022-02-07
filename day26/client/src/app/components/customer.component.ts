import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Customer } from '../models';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  customer!: Customer

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    lastValueFrom(
      this.http.get<Customer>('http://localhost:8080/api/customer')
    ).then(result => {
      console.info('>>> result = ', result)
      this.customer = result
    })
  }

}
