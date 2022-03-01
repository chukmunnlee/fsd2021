import { Component, NgZone, OnDestroy, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {BTN_CREATE_GAME, BTN_JOIN_GAME, BTN_REFRESH_GAME_IDS, CMD_JOIN, CMD_NEW, ControlAction} from '../models';
import {ChessService} from '../services/chess.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit, OnDestroy {

	gameIds: string[] = []

	sub$!: Subscription

	constructor(private chessSvc: ChessService, private router: Router, private zone: NgZone) { }

	ngOnInit() {
		this.chessSvc.getOpenGames()
			.then(result => this.gameIds = result)
			.catch(err => console.error(err))
	}

	ngOnDestroy() {
		this.sub$?.unsubscribe()
	}

	perform(cmd: ControlAction) {
		switch (cmd.command) {
			case BTN_JOIN_GAME:
				this.sub$ = this.chessSvc.joinGame(cmd?.gameId).subscribe(
					msg => {
						if (CMD_JOIN == msg.command)
							this.router.navigate([ '/game', cmd.gameId ])
					}
				)
				break

			case BTN_CREATE_GAME:
				this.sub$ = this.chessSvc.createGame().subscribe(
					msg => {
						if (CMD_NEW == msg.command)
							this.router.navigate([ '/game', this.chessSvc.gameId ])
					})
				break

			case BTN_REFRESH_GAME_IDS:
				this.ngOnInit()
				break

			default:
		}
	}

}
