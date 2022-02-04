import { Component, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Order } from '../models';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  orderForm!: FormGroup
  lineItemsArray!: FormArray

  @Output()
  onPlaceOrder = new Subject<Order>()

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.orderForm = this.createOrderForm();
    this.lineItemsArray = this.orderForm.get('lineItems') as FormArray
  }

  addLineItem() {
    this.lineItemsArray.push(this.createLineItem())
  }
  deleteLineItem(i: number) {
    this.lineItemsArray.removeAt(i)
  }

  processOrder() {
    const order = this.orderForm.value as Order
    this.ngOnInit()
    this.onPlaceOrder.next(order)
  }

  isValid() {
    return this.orderForm.valid && (this.lineItemsArray.length > 0)
  }

  private createLineItem(): FormGroup {
    return this.fb.group({
      description: this.fb.control('', [ Validators.minLength(3), Validators.required ]),
      quantity: this.fb.control(1, [ Validators.min(1), Validators.max(100), Validators.required ]),
    })
  }

  private createLineItems(c = 0): FormArray {
    const lis = this.fb.array([], [ Validators.minLength(1) ])
    for (let i = 0; i < c; i++)
      lis.push(this.createLineItem())
    return lis
  }

  private createOrderForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control('', [ Validators.minLength(3), Validators.required ]),
      address: this.fb.control('', [ Validators.minLength(5), Validators.required ]),
      phone: this.fb.control('', [ Validators.minLength(8), Validators.required ]),
      delivery: this.fb.control('normal'),
      comments: this.fb.control(''),
      lineItems: this.createLineItems()
    })
  }

}
