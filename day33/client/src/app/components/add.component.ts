import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, FormArray, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {LineItem, PurchaseOrder} from '../models';
import {PurchaseOrderService} from '../purchase-order.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

	lineItems!: FormArray
	po!: FormGroup

	constructor(private fb: FormBuilder, private router: Router
			, private poSvc: PurchaseOrderService) { }

	ngOnInit(): void { 
		this.po = this.createPurchaseOrder()
	}

	createPurchaseOrder(): FormGroup {
		this.lineItems = this.fb.array([], [ Validators.required ])
		return this.fb.group({
			name: this.fb.control('', [ Validators.required, Validators.minLength(3) ]),
			email: this.fb.control('', [ Validators.required, Validators.email ]),
			lineItems: this.lineItems
		})
	}

	placeOrder() {
		const po = this.po.value as PurchaseOrder
		po.lineItems = po.lineItems.map(v => {
			return {
				description: v.description,
				//@ts-ignore
				quantity: parseFloat(v.quantity),
				//@ts-ignore
				unitPrice: parseFloat(v.unitPrice),
			} as LineItem
		})
		this.poSvc.postPurchaseOrder(po)
			.then(result => {
				this.po = this.createPurchaseOrder();
				alert(`Order created. Your order id is ${result.orderId}`)
				this.router.navigate(['/'])
			})
			.catch(error => alert(`Error: ${JSON.stringify(error)}`))
	}

	addLineItem() {
		this.lineItems.push(
			this.fb.group({
				description: this.fb.control('', [ Validators.required, Validators.minLength(3) ]),
				quantity: this.fb.control('1', [ Validators.required, Validators.min(1) ]),
				unitPrice: this.fb.control('', [ Validators.required, Validators.min(0.1) ])
			})
		)
	}

	deleteLineItem(idx: number) {
		this.lineItems.removeAt(idx)
	}


}
