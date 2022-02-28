import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs'
import {WebcamImage} from 'ngx-webcam';
import {Router} from '@angular/router';
import {CameraService} from '../camera.service';

@Component({
  selector: 'app-camera',
  templateUrl: './camera.component.html',
  styleUrls: ['./camera.component.css']
})
export class CameraComponent implements OnInit {


	private _trigger$ = new Subject<void>();
	get triggerObservable() {
		return this._trigger$.asObservable()
	}

	constructor(private router: Router, private cameraSvc: CameraService) { }

	ngOnInit(): void { }

	handleImage(img: WebcamImage) {
		console.info('img = ', img)
		console.info('buffer = ', img.imageData)
		this.cameraSvc.image = img
		this.router.navigate(['/post'])
	}
	webcamInitError(error: any) {
		console.error('error = ', error)
	}

	snap() {
		this._trigger$.next()
	}

}
