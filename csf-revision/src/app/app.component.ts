import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  count = 0

  processEvent(count: number) {
    console.info('button clicked')
    this.count = count
  }
}
