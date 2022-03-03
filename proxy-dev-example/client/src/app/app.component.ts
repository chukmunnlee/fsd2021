import { Component, OnInit } from '@angular/core';
import {GameService} from './game.service';
import {Game} from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

	games: Game[] = []

	constructor(private gameSvc: GameService) { }

	ngOnInit() {
		this.gameSvc.listGames()
			.then(result => this.games = result)
			.catch(error => console.error('Error: ', error))
	}
}
