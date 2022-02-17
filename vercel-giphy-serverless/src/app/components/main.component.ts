import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

	form!: FormGroup

  constructor(private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
	  this.form = this.fb.group({
		  q: this.fb.control('', [ Validators.required ])
	  })
  }

	search() {
		const queryParams = {
			q: this.form.value['q']
		}
		this.router.navigate([ '/giphy' ], { queryParams })
	}

}
