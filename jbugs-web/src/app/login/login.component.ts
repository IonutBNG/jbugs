import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth-service/auth.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {$} from "protractor";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {

  loginUserData = {};
  private captchaResponse = null;

  constructor(private auth: AuthService,
              private router: Router,
              private toast: ToastrService) {
  }

  resolved(captchaResponse: string){
    this.captchaResponse = captchaResponse;
  }


  ngOnInit() {

  }

  loginUser(){
    if (this.captchaResponse != null && this.loginUserData["username"] && this.loginUserData["password"]) {
      this.auth.loginUser(this.loginUserData)
        .subscribe(
          res => {
            console.log(res);
            this.checkToken(res);
          },
          err => {
            console.log(err);
          }
        )
    }
  }

  private checkToken(res){
    if (res.token == undefined){
      this.showToast(res.message);
    } else if (res.token == ""){
      this.showToast("Invalid username or password !");
    } else {
      localStorage.setItem('token', res.token);
      this.router.navigate(['/usertable']);
    }
  }

  private showToast(message){
    this.toast.error(message, "Error");
  }

}
