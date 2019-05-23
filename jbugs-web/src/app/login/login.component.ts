import { Component, OnInit } from '@angular/core';
import {UserModel} from "../user-model/user-model";
import {UserService} from "../services/user-service/user.service";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService) {

  }

  public loggedUser: UserModel;

  public user: UserModel;

  ngOnInit() {

  }

  login(username, password){
    var user: UserModel = {username: username, password: password};
    console.log(user);
    console.log(user);
    this.userService.loginUser(user).subscribe(u => {
      this.loggedUser = u;
      console.log(this.loggedUser);
    })
    // this.userService.getUser()
    //   .subscribe((user) => {
    //     this.user = user;
    //     console.log(this.user);
    //   });
  }

}
