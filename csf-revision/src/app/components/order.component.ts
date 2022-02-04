import { Component, ElementRef, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { LineItem, Order } from '../models';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  @ViewChild('submitButton')
  submitButton!: ElementRef

  orderForm!: FormGroup
  lineItemsArray!: FormArray

  @Input()
  order: Partial<Order> = {}

  @Output()
  onPlaceOrder = new Subject<Order>()

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.setOrder(this.order)
    this.lineItemsArray = this.orderForm.get('lineItems') as FormArray
    console.info('>>> button elementRef:', this.submitButton)
  }

  setOrder(o: Partial<Order>) {
    this.orderForm = this.createOrderForm(o)
    console.info('>>> button elementRef:', this.submitButton)
  }

  addLineItem() {
    this.lineItemsArray.push(this.createLineItem())
  }
  deleteLineItem(i: number) {
    this.lineItemsArray.removeAt(i)
  }

  processOrder() {
    if (!this.isValid()) {
      alert('Your order is not valiid')
      return
    }
    const order = this.orderForm.value as Order
    this.orderForm = this.createOrderForm();
    this.lineItemsArray = this.orderForm.get('lineItems') as FormArray
    this.onPlaceOrder.next(order)
  }

  isValid() {
    return this.orderForm.valid && (this.lineItemsArray.length > 0)
  }

  private createLineItem(li: Partial<LineItem> = {}): FormGroup {
    return this.fb.group({
      description: this.fb.control(li?.description || '', [ Validators.minLength(3), Validators.required ]),
      quantity: this.fb.control(li?.quantity || 1, [ Validators.min(1), Validators.max(100), Validators.required ]),
    })
  }

  private createLineItems(li: LineItem[] = []): FormArray {
    const lis = this.fb.array([], [ Validators.minLength(1) ])
    for (let l of li)
      lis.push(this.createLineItem(l))
    return lis
  }

  private createOrderForm(order: Partial<Order> = {}): FormGroup {
    return this.fb.group({
      name: this.fb.control(order?.name || '', [ Validators.minLength(3), Validators.required ]),
      address: this.fb.control(order?.address || '', [ Validators.minLength(5), Validators.required ]),
      phone: this.fb.control(order?.phone || '', [ Validators.minLength(8), Validators.required ]),
      delivery: this.fb.control(order?.delivery || 'normal'),
      comments: this.fb.control(order?.comments || ''),
      lineItems: this.createLineItems(order?.lineItems)
    })
  }

}
