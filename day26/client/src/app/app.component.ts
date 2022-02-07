import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { from, lastValueFrom, map, Subscription, take } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  result: any = {}
  origin = ''
  userAgent = ''

  sub$!: Subscription

  // Get the Http Service
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    //this.asPromise('https://httpbin.org/get')
    //this.asObservable('https://httpbin.org/get')
    this.asObservableFromPromise('https://httpbin.org/get')
  }

  ngOnDestroy(): void {
    this.sub$?.unsubscribe()
  }

  asObservable(url: string) {
    this.sub$ = this.http.get<any>(url)
      .pipe(
        map(result => {
          return { origin: result.origin, userAgent: result.headers['User-Agent'] }
        })
      )
      .subscribe(result => {
          console.info('using observable')
          this.result = result
          this.origin = result.origin
          this.userAgent = result.userAgent
      })
  }

  asObservableFromPromise(url: string) {
    const result$ = this.http.get<any>(url)
    // Promise -> Observable
    lastValueFrom(
      result$
        .pipe(
          map(result => {
            return { origin: result.origin, userAgent: result.headers['User-Agent'] }
          }),
        )
    ).then(result => {
      console.info('using observable from promise')
      this.result = result
      this.origin = result.origin
      this.userAgent = result.userAgent
    })
    /*
    this.sub$ = from(lastValueFrom(result$))
      .pipe(
        map(result => {
          return { origin: result.origin, userAgent: result.headers['User-Agent'] }
        })
      )
      .subscribe(result => {
          console.info('using observable from promise')
          this.result = result
          this.origin = result.origin
          this.userAgent = result.userAgent
      })
      */
  }

  asPromise(url: string) {
    // returns an observable, convent is to suffix an observable variable with $
    const result$ = this.http.get<any>(url)
    // convert the observable into a promise
    // Observable -> Promise
    lastValueFrom(result$)
      .then(result => {
        return { origin: result.origin, userAgent: result.headers['User-Agent'] }
      })
      .then(result => {
        // result = { origin: '...', userAgent: '... '}
        this.result = result
        this.userAgent = result.userAgent
        this.origin = result.origin
      })
      .catch(error => {
        this.result = error
      })

  }

}
