import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {PermsAssignments} from "../../user-model/perms-assignments";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = "/jbugs/jbugs-api/user/authenticate";

  private permsAssignments: PermsAssignments = {
    isBugC: false,
    isPermsM: false,
    isBugM: false,
    isUserM: false
  };

  constructor(private http: HttpClient) { }

  loginUser(user){
    console.log(this.loginUrl);
    return this.http.post<any>(this.loginUrl, user);
  }

  public setPermissionsRouting(): PermsAssignments{
    const perms = localStorage.getItem("permissions");
    console.log(perms);
    var array = perms.split(',');
    if (array.includes('USER_MANAGEMENT'))
      this.permsAssignments.isUserM = true;
    if (array.includes('BUG_MANAGEMENT'))
      this.permsAssignments.isBugM = true;
    if (array.includes('PERMISSION_MANAGEMENT'))
      this.permsAssignments.isPermsM = true;
    if (array.includes('BUG_CLOSE'))
      this.permsAssignments.isBugC = true;
    return this.permsAssignments;
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
    localStorage.removeItem('permissions');
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
