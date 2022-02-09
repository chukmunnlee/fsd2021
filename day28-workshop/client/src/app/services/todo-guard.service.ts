import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanDeactivate, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";
import {TodoGuard} from "../models";

@Injectable()
export class TodoGuardService implements CanDeactivate<TodoGuard> {

	canDeactivate( component: TodoGuard, 
			currentRoute: ActivatedRouteSnapshot, 
			currentState: RouterStateSnapshot, nextState?: RouterStateSnapshot)
		: boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {

		if (!component.evaluate())
			return new Promise((resolve, reject) => {
				resolve(confirm('You have not saved your data.\nAre you sure you want to leave?'))
			})

		return true
	}

}
