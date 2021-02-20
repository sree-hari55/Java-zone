import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RegisterPayload } from './registerpayload';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  registerpayload: RegisterPayload;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private authService: AuthService,private  router:Router) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword:['', Validators.required]
    },{
      validator: mustMatch('password', 'confirmPassword')
  });

    this.registerpayload = ({
      userName: '',
      email: '',
      password: '',
      confirmPassword: ''
    });
  }
  get fval() {
    return this.registerForm.controls;
    }

  ngOnInit(): void {
  }

  
  onSubmit() {
    this.submitted = true;

       // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.registerpayload.userName = this.registerForm.get('username').value;
    this.registerpayload.email = this.registerForm.get('email').value;
    this.registerpayload.password = this.registerForm.get('password').value;
    this.registerpayload.confirmPassword = this.registerForm.get('confirmPassword').value; 
    this.authService.register(this.registerpayload).subscribe(data => {
      console.log(data);
      this.router.navigateByUrl('/register-sucess');
    }, error => {
      console.log(error);
    }); 
  }
}

// custom validator to check that two fields match
export function mustMatch(controlName: string, matchingControlName: string) {
  return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      if (matchingControl.errors && !matchingControl.errors.mustMatch) {
          // return if another validator has already found an error on the matchingControl
          return;
      }

      // set error on matchingControl if validation fails
      if (control.value !== matchingControl.value) {
          matchingControl.setErrors({ mustMatch: true });
      } else {
          matchingControl.setErrors(null);
      }
  }
}