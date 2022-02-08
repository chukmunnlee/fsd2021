import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, CanDeactivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { Deactivating } from "./models";

@Injectable()
export class NumberGuardService implements CanActivate, CanDeactivate<Deactivating> {

  constructor(private router: Router) { }

  canActivate(activatedRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot):
      boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {

        let num = parseInt(activatedRoute.params['num'])

        console.info('can activate: ', num)

        //return (num % 2) <= 0

        /*
        return new Promise(
          (resolve, reject) => {
            if ((num % 2) > 0) {
              alert('We will not allow odd folks in')
              resolve(false)
              //reject()
            }
            resolve(true)
          }
        )
        */
       /*
       return new Promise(
         (resolve, reject) => {
           if ((num % 2) > 0)
             return resolve(confirm('Its an odd number Proceed?'))
          return resolve(true)
         }
       )
       */

      if ((num % 2) > 0) {
        num++
        // returns a UrlTree
        return this.router.createUrlTree([ '/number', num ])
      }
      return true
  }

  canDeactivate(component: Deactivating,
      currentRoute: ActivatedRouteSnapshot, currentState: RouterStateSnapshot,
      nextState?: RouterStateSnapshot)
    : boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {

    if (!component.evaluate())
      return new Promise(
        (resolve, reject) => {
          resolve(confirm('Are you sure you want to leave?'))
        }
      )

    return true
  }

}
