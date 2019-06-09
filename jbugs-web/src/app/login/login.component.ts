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
      console.log("[Login Comp] TOKEN " + res.token);
      localStorage.setItem('token', res.token);
      localStorage.setItem('permissions', res.permissions);
      this.router.navigate(['/home']);
    }
  }

  private showToast(message){
    this.toast.error(message, "Error");
  }

}
