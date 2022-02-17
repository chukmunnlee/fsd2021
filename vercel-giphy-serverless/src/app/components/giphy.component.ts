import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GiphyService} from '../giphy.service';
import { Giphy } from '../models'

@Component({
  selector: 'app-giphy',
  templateUrl: './giphy.component.html',
  styleUrls: ['./giphy.component.css']
})
export class GiphyComponent implements OnInit {

	q = ""
  gifs: Giphy[] = []

  constructor(private activatedRoute: ActivatedRoute,
  		private giphySvc: GiphyService) { }

  ngOnInit(): void {
	  this.q = this.activatedRoute.snapshot.queryParams['q']
    this.giphySvc.search(this.q)
      .then(result => {
        console.info('>>> result: ', result)
        this.gifs = result
      })
  }

}
