import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth-service/auth.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {ImagesService} from "../services/images-service/images.service";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {

  private loginUserData = {username: "", password: ""};
  private captchaResponse = null;

  showFiller = true;


  constructor(private auth: AuthService,
              private router: Router,
              private toast: ToastrService,
              private imagesService: ImagesService) {
  }

  resolved(captchaResponse: string){
    this.captchaResponse = captchaResponse;
  }


  ngOnInit() {
  }

  loginUser(){
    if (!this.loginUserData['username'])
      this.showToastError("Please enter a username");
    else if (!this.loginUserData['password'])
      this.showToastError("Please enter a password");
    else if (this.captchaResponse === null)
      this.showToastError("Please complete the captcha");
    else {
      this.auth.loginUser(this.loginUserData)
        .subscribe(
          res => {
            console.log(res);
            this.checkToken(res);
          });
    }
  }

  private checkToken(res){
    if (res.token == undefined){
      this.showToastError(res.message);
    } else if (res.token == ""){
      this.showToastError("Invalid username or password !");
    } else {
      console.log("[Login Comp] TOKEN " + res.token);
      localStorage.setItem('token', res.token);
      localStorage.setItem('permissions', res.permissions);
      this.router.navigate(['/home']);
    }
  }

  private showToastError(message){
    this.toast.error(message, "Error");
  }

}
