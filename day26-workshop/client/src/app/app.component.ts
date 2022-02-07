import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Registration } from './models';
import { RegistrationService } from './registration.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  form!: FormGroup

  constructor(private fb: FormBuilder, private registerSvc: RegistrationService) {}

  ngOnInit(): void {
    this.form = this.createForm()
  }

  processForm() {
    const reg: Registration = this.form.value as Registration
    console.info('>>>> reg: ', reg)

    this.registerSvc.postRegistration(reg)
      .then(result => {
        this.form.reset();
        console.info('>>>> result: ', result)
      })
      .catch(error => {
        alert('An error has ocurred')
        console.error('>>> error: ', error)
      })
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control('', [ Validators.required, Validators.minLength(3) ]),
      email: this.fb.control('', [ Validators.required, Validators.email ]),
    })
  }
}
