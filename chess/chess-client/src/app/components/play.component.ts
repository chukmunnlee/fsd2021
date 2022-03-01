import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import {Title} from '@angular/platform-browser';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {ChessEvent, CMD_MOVE, CMD_START} from '../models';
import {ChessService} from '../services/chess.service';
import {BoardComponent} from './board.component';

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.css']
})
export class PlayComponent implements OnInit, OnDestroy, AfterViewInit {

	@ViewChild(BoardComponent)
	board!: BoardComponent

	sub$!: Subscription
	gameId!: string

	constructor(private router: Router, private activatedRoute: ActivatedRoute
			, private titleSvc: Title, private chessSvc: ChessService) { }

	ngOnInit(): void { 
		this.gameId = this.activatedRoute.snapshot.params['gid']
		this.titleSvc.setTitle(`Game: ${this.gameId}`)
	}
	ngOnDestroy() {
		this.sub$?.unsubscribe()
	}

	ngAfterViewInit() {
		this.sub$ = this.chessSvc.subj$.subscribe(
			msg => {
				switch (msg.command) {
					case CMD_START:
						this.board.createGame(msg.player)
						break

					case CMD_MOVE:
						console.info('>>>> move ', msg)
						// @ts-ignore
						this.board.move(msg.src, msg.dest)
						break

					default:
				}
			}
		);
	}

	chessEvent(evt: ChessEvent) {
		console.info('>>> chessEvent: ', evt)
		this.chessSvc.movePiece(evt)
	}

}
