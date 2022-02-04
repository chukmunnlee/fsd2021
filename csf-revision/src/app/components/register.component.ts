import { Component, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { BehaviorSubject, Subject } from 'rxjs';
import { Registration } from '../models';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  @Input()
  message = "hello, world"

  @Output()
  clicked = new Subject<number>()

  form!: FormGroup
  emailCtrl!: FormControl
  nameField = "name"

  hobbies = [ 'Read', 'Jog', 'Sleep' ]
  hobbiesArray!: FormArray

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.createForm();
    this.emailCtrl = this.form.get('email') as FormControl
    //console.info('emailCtrl: ', this.emailCtrl)
  }

  processForm() {
    console.info('>>> form: ', this.form.value)
    const data = { ...this.form.value } as Registration
    data.hobbies = data.hobbies.map(v => !!v)
    console.info('>>> data: ', data)
  }

  createForm(): FormGroup {
    this.hobbiesArray = this.fb.array([])
    for (let i = 0; i < this.hobbies.length; i++)
      this.hobbiesArray.push(this.fb.control(''))

    return this.fb.group({
      name: this.fb.control('', [ Validators.minLength(3), Validators.required ]),
      email: this.fb.control('fred@gmail.com', [ Validators.email, Validators.required ]),
      gender: this.fb.control('', [ Validators.required ]),
      hobbies: this.hobbiesArray
    })
  }


}
