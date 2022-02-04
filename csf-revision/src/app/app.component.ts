import { Component } from '@angular/core';
import { Order } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  count = 0

  processOrder(order: Order) {
    console.info('app.component process order: ', order)
  }
  processEvent(count: number) {
    console.info('button clicked')
    this.count = count
  }
}
