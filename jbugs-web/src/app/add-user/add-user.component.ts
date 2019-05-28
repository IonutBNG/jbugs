import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user-service/user.service";
import {NewUserModel} from "../user-model/new-user-model";
import {MatDialogRef} from "@angular/material";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {

  constructor(private userService: UserService,
              private dialogRef: MatDialogRef<AddUserComponent>,
              private toast: ToastrService) { }

  public addedUserSuccesfully:boolean;

  public errorJson:Object;

  ngOnInit() {
  }

  addNewUser(firstname: string,  lastName: string,  mobileNumber: string,  email: string,  password: string) {
    var newUser: NewUserModel ={firstName:firstname, lastName:lastName,mobileNumber:mobileNumber,email:email, password:password}
    this.userService.addNewUser(newUser).subscribe( res => {
      console.log(res);
      // if(!this.okResponse(res)) {
      //   this.errorJson = res;
      // }
      this.okResponse(res);
    },
      err => {
      console.log(err)
      });

  }

  okResponse(res) {
    if (res.status === undefined) {
      this.addedUserSuccesfully = false;
      this.closeDialogError(res.message);
    }
    else {
      this.addedUserSuccesfully = true;
      this.closeDialogSuccess(res.status);
    }
  }

  private closeDialogError(message){
    this.toast.error(message, "Error");
  }


  private closeDialogSuccess(message){
    this.dialogRef.close();
    this.toast.success(message, "Success");
  }

  private close(){
    this.dialogRef.close();
  }


}
