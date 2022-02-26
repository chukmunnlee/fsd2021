import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {lastValueFrom} from "rxjs";
import {PostPurchaseOrderResult, PurchaseOrder, PurchaseOrderTotal} from "./models";

@Injectable()
export class PurchaseOrderService {

	constructor(private http: HttpClient) { }

	postPurchaseOrder(po: PurchaseOrder): Promise<PostPurchaseOrderResult> {
		return lastValueFrom(
			this.http.post<PostPurchaseOrderResult>("/api/order", po)
		)
	}

	getPurchaseOrderValues(): Promise<PurchaseOrderTotal[]> {
		return lastValueFrom(
			this.http.get<PurchaseOrderTotal[]>('/api/order/values')
		)
	}
}
