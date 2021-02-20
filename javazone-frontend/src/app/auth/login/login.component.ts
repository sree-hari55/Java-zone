import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { LoginPayload } from './loginpayload';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loginPayload:LoginPayload;
  submitted = false;

  constructor(private formBuilder: FormBuilder,private authService:AuthService,private router:Router) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
this.loginPayload = {
      userName: '',
      password: ''
    };
   }

   get fval() {
    return this.loginForm.controls;
    }

  ngOnInit(): void {
  }
  

  onSubmit(){
    this.submitted = true;

    // stop here if form is invalid
  if (this.loginForm.invalid) {
    return;
  }

  
  this.loginPayload.userName=this.loginForm.get('username').value;
  this.loginPayload.password=this.loginForm.get('password').value;
  this.authService.login(this.loginPayload).subscribe(data => {
    if (data) {
      console.log('login success');
      this.router.navigateByUrl('/home');
    } else {
      console.log('Login failed'+data);
    }
  });
}
}
