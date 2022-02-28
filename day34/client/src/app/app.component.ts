import {HttpClient} from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {WebcamImage} from 'ngx-webcam';
import {Subject} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

	form!: FormGroup
	private _trigger$ = new Subject<void>();

	get triggerObservable() {
		return this._trigger$.asObservable()
	}

	constructor(private fb: FormBuilder, private http: HttpClient) { }

	ngOnInit() {
		this.form = this.fb.group({
			poster: this.fb.control('fred', [ Validators.required ]),
			comments: this.fb.control('', [ Validators.required ]),
		})
	}

	handleImage(img: WebcamImage) {
		console.info('img = ', img)
	}
	webcamInitError(error: any) {
		console.error('error = ', error)
	}

	snap() {
		this._trigger$.next()
	}

	post() {
		const post = this.form.value
		console.info('post = ', post)
	}
}
