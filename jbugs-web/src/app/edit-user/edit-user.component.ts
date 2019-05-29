import { Component, OnInit } from '@angular/core';
import {EditUserModel} from "../user-model/edit-user";
import {UserService} from "../services/user-service/user.service";
import {NewUserModel} from "../user-model/new-user-model";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss']
})
export class EditUserComponent implements OnInit {

  public currentUser:EditUserModel;

  public editedUserSuccesfully;

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  editUser(userName: string, firstname: string,  lastName: string,  mobileNumber: string,  email: string,  password: string) {
    var editUser: EditUserModel ={userName:userName, firstName:firstname, lastName:lastName,mobileNumber:mobileNumber,email:email, password:password};
    this.userService.addNewUser(this.currentUser).subscribe( res => {
        console.log(res);
        if(!this.okResponse(res)) {

        }
      },
      err => {
        console.log(err)
      });

  }

  okResponse(res) {
    if (res.status === undefined) {
      this.editedUserSuccesfully = false;
    }

    if (res.status === "OK") {
      this.editedUserSuccesfully=true;
    }
    else {
      return false;
    }
  }

}
