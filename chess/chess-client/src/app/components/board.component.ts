import { Component, OnInit, Output } from '@angular/core';
import {Subject} from 'rxjs';
import {ChessEvent} from '../models';
import {ChessService} from '../services/chess.service';

const { Chessboard } = require('@ggblee/chessboardjs/dist/chessboard')

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

	private board!: any

	@Output()
	onChessEvent = new Subject<ChessEvent>();

	constructor(private win: Window) { }

	ngOnInit(): void {
		this.board = Chessboard('chessboard')
	}

	createGame(player: string) {
		const config = {
			position: 'start',
			pieceTheme: 'assets/img/chesspieces/wikipedia/{piece}.png',
			orientation: player,
			draggable: true,
			onDrop: this.onDrop.bind(this)
		}
		console.info('>>>> config = ', config);
		this.board = Chessboard('chessboard', config)
	}

	private onDrop(src: string, dest: string, piece: string, newPos: string, oldPos: string, player: string) {
		console.info(`src: ${src}, dest: ${dest}, piece: ${piece}, player: ${player}`)
		this.onChessEvent.next({ src, dest, player })
	}

	move(src: string, dst: string) {
		this.board.move(`${src}-${dst}`)
	}

	onResize() {
		this.board.resize()
	}

}
