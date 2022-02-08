import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Deactivating } from '../models';

@Component({
  selector: 'app-number',
  templateUrl: './number.component.html',
  styleUrls: ['./number.component.css']
})
export class NumberComponent implements OnInit, Deactivating {

  num: number = 1
  form!: FormGroup

  constructor(private fb: FormBuilder, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.num = parseInt(this.activatedRoute.snapshot.params['num'])
    this.form = this.fb.group({
      comments: this.fb.control('', [ Validators.required ])
    })
  }

  reset() {
    console.info('adding form')
    // clear from first before navigating back if CanDeactivate is present
    this.form.reset()
    this.router.navigate(['/'])
  }

  evaluate(): boolean | Promise<boolean> {
    const cond = this.form.valid && this.form.dirty
    console.info('canDeactivate cond: ', cond)
    return !(this.form.valid && this.form.dirty)
  }

}
