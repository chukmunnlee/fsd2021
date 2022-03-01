import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {lastValueFrom, Subject} from "rxjs";

import { ChessEvent, ChessMessage, CMD_JOIN, CMD_NEW, CMD_START, CMD_MOVE } from '../models'
import {mkJoinMessage, mkMoveMessage} from "../utils";

@Injectable()
export class ChessService {

	server: string
	ws: string;

	private _gameId = ""
	get gameId() { return this._gameId }

	private _owner = false
	get owner() { return this._owner } 

	sock!: WebSocket
	subj$!: Subject<ChessMessage>;

	constructor(private win: Window, private http: HttpClient) {
		this.server = this.win.location.host
		this.ws = `ws://${this.server}/game`
	}


	getOpenGames(): Promise<string[]> {
		return lastValueFrom(this.http.get<string[]>('games/open'))
	}

	createGame() {
		return this.initSocket()
	}

	joinGame(gameId: string) {
		this.initSocket()
		this.sock.onopen = () => {
			console.info('Sending join message')
			this.sock.send(mkJoinMessage(gameId))
		}
		return this.subj$
	}

	movePiece(evt: ChessEvent) {
		this.sock.send(mkMoveMessage(this.gameId, evt.src, evt.dest, evt.player))
	}

	private initSocket() {

		this.subj$?.unsubscribe();

		this.subj$ = new Subject<ChessMessage>()

		this.sock = new WebSocket(this.ws)
		this.sock.onopen = (event) => {
			console.info('connection opened: ', event)
		}
		this.sock.onclose = (event) => {
			console.info('connection closed: ', event)
		}
		this.sock.onerror = (event) => {
			console.info('connection error: ', event)
		}

		this.sock.onmessage = this.onMessage.bind(this)

		return this.subj$
	}

	private onMessage(event: any) {
		const gm = JSON.parse(event.data) as ChessMessage

		switch (gm.command) {
			case CMD_NEW:
				this._gameId = gm.gameId
				this._owner = true
				break

			case CMD_JOIN:
				this._gameId = gm.gameId
				break

			case CMD_START:
				break

			case CMD_MOVE:
				break;

			default:

		}

		this.subj$.next(gm)
	}
}
