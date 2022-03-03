import { Component, OnInit } from '@angular/core';
import {makeStateKey, TransferState} from '@angular/platform-browser';
import {GameService} from './game.service';
import {Game} from './models';

const gamesKey = makeStateKey<Game[]>('games')

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

	games: Game[] = []

	constructor(private gameSvc: GameService, private transferState: TransferState) { }

	ngOnInit() {
		if (this.transferState.hasKey(gamesKey)) 
			this.games = this.transferState.get(gamesKey, []);

		else
			this.gameSvc.listGames()
				.then(result => this.games = result)
				.catch(error => console.error('Error: ', error))
	}
}
