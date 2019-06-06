import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = "/jbugs/jbugs-api/user/authenticate";


  constructor(private http: HttpClient,
              private router: Router) { }

  loginUser(user){
    console.log(this.loginUrl);
    return this.http.post<any>(this.loginUrl, user);
  }

  getDecodedToken(){
    return this.parseJwt(localStorage.getItem('token'));
    // return localStorage.getItem('token');
  }

  loggedIn(){
    return !!localStorage.getItem('token');
  }

  logOut(){
    localStorage.removeItem('token');
    // this.router.navigate(['/login']);
  }

  parseJwt (token) {
    let base64Url = token.split('.')[1];
    let base64 = decodeURIComponent(atob(base64Url).split('').map(function(c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    let username = JSON.parse(base64);
    let uname = username['iss'];

    return uname;
  };

}
