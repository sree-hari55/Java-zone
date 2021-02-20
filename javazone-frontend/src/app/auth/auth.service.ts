import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisterPayload } from './register/registerpayload';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { LoginPayload } from './login/loginpayload';
import { LocalStorageService } from 'ngx-webstorage';
import { JwtAutResponse } from './login/jwt-auth-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  response:any;
  private url = '/api/auth/';  
  
  constructor(private httpClient:HttpClient,private localStorageService: LocalStorageService) { }

  register(registerPayload:RegisterPayload):Observable<any> {
  return this.httpClient.post(this.url + 'signUp', registerPayload,{responseType: 'text'});
  }

  login(loginPayload:LoginPayload):Observable<any>{
    return this.httpClient.post<JwtAutResponse>(this.url + 'login', loginPayload).pipe(map(data => {
      this.localStorageService.store('authenticationToken', data.authenticationToken);
      this.localStorageService.store('username', data.username);
      return true;
    }));
  }

  isAuthenticated() :boolean{
    return this.localStorageService.retrieve('username') != null;
  }

  logout(){
    this.localStorageService.clear('authenticationToken');
    this.localStorageService.clear('username');
  }
}
