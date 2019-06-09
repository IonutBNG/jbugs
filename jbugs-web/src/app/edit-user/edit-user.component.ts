import {Component, OnInit, Optional} from '@angular/core';
import {EditUserService} from "../services/edit-user-service/edit-user.service";
import {User} from "../user-model/user-table";
import {UserService} from "../services/user-service/user.service";
import {EditUserModel} from "../user-model/edit-user";
import {ToastrService} from "ngx-toastr";
import {MatDialogRef} from "@angular/material";
import {UserEmitterService} from "../services/user-emitter-service/user-emitter.service";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss']
})
export class EditUserComponent implements OnInit {

  private editedUser:User;
  private username: string;
  private firstName: string;
  private lastName: string;
  private mobileNumber: string;
  private email: string;
  private counter: number;

  constructor(private editUserService: EditUserService,
              private userService: UserService,
              private toast: ToastrService,
              private userEmitter: UserEmitterService,
              @Optional() private dialogRef: MatDialogRef<EditUserComponent>) { }

  ngOnInit() {
    this.setUser();
  }

  private editedNewUser: EditUserModel;
  private addedUserSuccesfully: boolean;

  private setUser() {
    this.editUserService.currentEditedUser.subscribe(user => this.editedUser = user);
    this.firstName = this.editedUser.firstName;
    this.lastName = this.editedUser.lastName;
    this.email = this.editedUser.email;
    this.mobileNumber = this.editedUser.mobileNumber;
    this.username = this.editedUser.username;
    this.counter = this.editedUser.counter;
    console.log(this.username);
  }

  private editNewUser(username: string, firstName: string, lastName: string, mobileNumber: string, email: string,
                      counter: number, password: string, confirmPassword: string) {
    var same = this.checkSamePassword(password, confirmPassword);
    if (same === false)
      this.closeDialogError("Please confirm with the same password!");
    else {
      this.setEditedUser(username, firstName, lastName, mobileNumber, email, counter, password);
      console.log(this.editedNewUser);
      this.userService.editUser(this.editedNewUser).subscribe(res => {
          this.checkResponse(res);
        })
    }
  }

  private checkSamePassword(password: string, confirmPassword: string): boolean{
    if (password === confirmPassword)
      return true;
    return false;
  }

  private setEditedUser(username: string, firstName: string, lastName: string, mobileNumber: string, email: string,
                  counter: number, password: string){
    const user: EditUserModel = {
      username: username,
      firstName: firstName,
      lastName: lastName,
      mobileNumber: mobileNumber,
      email: email,
      password: password,
      counter: counter
    };
    this.editedNewUser = user;
  }

  private checkResponse(res) {
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

  private closeDialogSuccess(message: string){
    this.dialogRef.close();
    this.userEmitter.doSomething();
    this.toast.success(message, "Success");
  }

  private close() {
    this.dialogRef.close();
  }

}
