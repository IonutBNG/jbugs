import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user-service/user.service";
import {NewUserModel} from "../user-model/new-user-model";

@Component({
  selector: 'add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {

  constructor(private userService: UserService) { }

  public currentUser:NewUserModel;

  ngOnInit() {
  }

  addNewUser(firstname: string,  lastName: string,  mobileNumber: string,  email: string,  password: string) {
    var newUser: NewUserModel ={firstName:firstname, lastName:lastName,mobileNumber:mobileNumber,email:email, password:password}
    this.userService.addNewUser(newUser).subscribe( u => {this.currentUser = u});
    console.log(newUser);

  }

}
