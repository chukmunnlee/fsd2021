import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom, Observable } from "rxjs";

import { Customer } from './models'

@Injectable()
export class CustomerService {

  constructor(private http: HttpClient) { }

  getCustomer(): Promise<Customer> {
    return lastValueFrom(
      this.http.get<Customer>('http://localhost:8080/api/customer')
    )
  }
  getCustomerAsObservable(): Observable<Customer> {
    return this.http.get<Customer>('http://localhost:8080/api/customer')
  }

}
