import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup , Validators } from '@angular/forms'
import { HttpClient } from '@angular/common/http'
import {CameraService} from '../camera.service';
import {Router} from '@angular/router';
import {Post} from '../models';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

	form!: FormGroup
	imageData = ''

	constructor(private fb: FormBuilder, private http: HttpClient
			, private cameraSvc: CameraService, private router: Router) { }

	ngOnInit() {

		if (!this.cameraSvc.image) {
			this.router.navigate(['/'])
			return
		}

		this.imageData = this.cameraSvc.image.imageAsDataUrl

		this.form = this.fb.group({
			poster: this.fb.control('fred', [ Validators.required ]),
			comments: this.fb.control('', [ Validators.required ]),
		})
	}

	post() {
		const post = {
			poster: this.form.value['poster'],
			comments: this.form.value['comments'],
			photo: this.cameraSvc.image

		} as Post
		console.info('post = ', post)
		this.cameraSvc.post(post)
			.then(result => {
				console.info(">> ok: ", result)
				this.router.navigate(['/'])
			})
			.catch(error => {
				console.error("error: ", error)
			})

	}

}
