import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user-service/user.service";
import {NewUserModel} from "../user-model/new-user-model";

@Component({
  selector: 'add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {

  constructor(private userService: UserService) {
  }

  public addedUserSuccesfully:boolean;

  public errorJson:Object;

  ngOnInit() {
  }

  addNewUser(firstname: string,  lastName: string,  mobileNumber: string,  email: string,  password: string) {
    var newUser: NewUserModel ={firstName:firstname, lastName:lastName,mobileNumber:mobileNumber,email:email, password:password}
    this.userService.addNewUser(newUser).subscribe( res => {
      console.log(res);
      if(!this.okResponse(res)) {
        this.errorJson = res;
      }
    },
      err => {
      console.log(err)
      });

  }

  okResponse(res) {
    if (res.status === undefined) {
      this.addedUserSuccesfully = false;
    }

    if (res.status === "OK") {
      this.addedUserSuccesfully=true;
    }
    else {
      return false;
    }
  }

}
