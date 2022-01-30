import { Component } from '@angular/core';
import { AnimationOptions } from 'ngx-lottie';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  options: AnimationOptions = {
    path: 'assets/data.json'
  }

  width: string
  height: string

  constructor(private win: Window) {
    this.width = Math.floor(this.win.innerWidth * .7) + 'px'
    this.height = Math.floor(this.win.innerHeight * .7) + 'px'
  }
}
