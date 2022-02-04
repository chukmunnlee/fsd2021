import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { OrderComponent } from './components/order.component';
import { Order } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit {

  @ViewChild(OrderComponent)
  orderComponent!: OrderComponent

  count = 0

  order: Order = {
    name: 'fred',
    address: '1 bedrock ave',
    phone: '12345678',
    comments: 'please rush this order',
    delivery: 'normal',
    lineItems: [
      { description: 'apple', quantity: 10 },
      { description: 'pear', quantity: 10 },
    ]
  }

  ngOnInit(): void {
    console.info(">>> orderComponent: ", this.orderComponent)
  }

  ngAfterViewInit(): void {
    console.info(">>> after view init orderComponent: ", this.orderComponent)
    this.orderComponent.setOrder(this.order)

  }

  updateOrder() {
    console.info('>> updating order')
  }

  processOrder(order: Order) {
    console.info('app.component process order: ', order)
  }
  processEvent(count: number) {
    console.info('button clicked')
    this.count = count
  }
}
