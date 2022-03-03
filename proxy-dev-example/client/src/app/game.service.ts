import {HttpClient, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {lastValueFrom} from "rxjs";
import {Game} from "./models";

@Injectable()
export class GameService {

	constructor(private http: HttpClient) { }

	listGames(limit = 20, offset = 0): Promise<Game[]> {
		const params = new HttpParams().set('limit', limit).set('offset', offset)
		return lastValueFrom(
			this.http.get<Game[]>('/api/games', { params })
		)
	}
}
