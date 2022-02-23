import {HttpClient, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {lastValueFrom} from "rxjs";
import {Book} from "./models";

@Injectable()
export class GoodreadsService {

	constructor(private http: HttpClient) { }

	getAllBooks(offset=0, limit=10): Promise<Book[]> {

		const params = new HttpParams().set("offset", offset).set("limit", limit)

		return lastValueFrom(
			this.http.get<Book[]>("/api/books", { params })
		)
	}
}
