import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth-service/auth.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {PermsAssignments} from "../user-model/perms-assignments";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {

  loginUserData = {};
  private captchaResponse = null;

  showFiller = true;
  private permsAssignments: PermsAssignments;

  image1 = "https://images.unsplash.com/photo-1506506200949-df8644f002d1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60";
  image2 = "https://images.unsplash.com/photo-1536148935331-408321065b18?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60";

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
      console.log("[Login Comp] TOKEN " + res.token);
      localStorage.setItem('token', res.token);
      localStorage.setItem('permissions', res.permissions);
      this.router.navigate(['/home']);
    }
  }

  // private navigateRouter(){
  //   this.permsAssignments = this.auth.setPermissionsRouting();
  //   if (this.permsAssignments.isUserM) {
  //     this.router.navigate(['/home/usertable']);
  //     return;
  //   }
  //   if (this.permsAssignments.isBugM) {
  //     this.router.navigate(['/home/bugtable']);
  //     return;
  //   }
  //   if (this.permsAssignments.isPermsM)
  //     this.router.navigate(['/home/permissions']);
  // }

  private showToast(message){
    this.toast.error(message, "Error");
  }

}
