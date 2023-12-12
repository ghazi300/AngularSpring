import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AppConstants} from "../commons/app.constant";
import {Subject} from "rxjs";
const httpOptions = {
  headers: new HttpHeaders({


    'Content-Type': 'application/json'
  })
};


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public loginStatusSubject =new Subject<boolean>();

  constructor(private http: HttpClient) { }

  signup(user: any) {
    return this.http.post('http://localhost:8080/api/auth/signup', user, httpOptions);
  }

  login(user: any) {
    return this.http.post('http://localhost:8080/api/auth/login', user);
  }

  getCurrentuser() {
    return this.http.get('http://localhost:8080/api/user/current-user');
  }

  test() {
    return this.http.get('http://localhost:8080/api/user');
  }
  getOtp(email:any)
  {
    return this.http.post('http://localhost:8080/api/auth/otp',email,{responseType:'text'});

  }
  resertPassword(email:any,otp:any,newPassword:any)

  {
    const body = { email, otp, newPassword };
    return this.http.post('http://localhost:8080/api/auth/resetPasswordDone',body,{responseType:'text'});

  }


}
