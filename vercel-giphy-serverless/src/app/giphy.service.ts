import {HttpClient, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {lastValueFrom} from "rxjs";

import { Giphy } from './models'

@Injectable()
export class GiphyService {

	constructor(private http: HttpClient) { }

	search(q: string): Promise<Giphy[]> {
		const params = new HttpParams().set('q', q)
		return lastValueFrom(
			this.http.get<Giphy[]>('/api/search-giphy', { params })
		)
	}

}
