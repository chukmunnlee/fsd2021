import { Component, OnInit } from '@angular/core';
import {PurchaseOrderTotal} from '../models';
import {PurchaseOrderService} from '../purchase-order.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

	pos: PurchaseOrderTotal[] = []

	constructor(private poSvc: PurchaseOrderService) { }

	ngOnInit(): void {
	  this.poSvc.getPurchaseOrderValues()
			.then(result => this.pos = result)
			.catch(error => alert(`Error: ${JSON.stringify(error)}`))
	}

}
