import { Component, OnInit } from '@angular/core';
import {GoodreadsService} from './goodreads-service';
import {Book} from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

	books: Book[] = []

	constructor(private goodreadsSvc: GoodreadsService) { }

	ngOnInit() { 
		this.goodreadsSvc.getAllBooks()
			.then(result => this.books = result)
	}
}
