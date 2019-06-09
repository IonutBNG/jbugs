import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Optional,
  Output,
} from '@angular/core';
import {UserService} from "../services/user-service/user.service";
import {NewUserModel} from "../user-model/new-user-model";
import {MatDialogRef} from "@angular/material";
import {ToastrService} from "ngx-toastr";
import {UserEmitterService} from "../services/user-emitter-service/user-emitter.service";

@Component({
  selector: 'add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {


  constructor(private userService: UserService,
              private toast: ToastrService,
              private userEmitter: UserEmitterService,
              @Optional() private dialogRef: MatDialogRef<AddUserComponent>) {

  }

  public addedUserSuccesfully:boolean;

  ngOnInit() {


  }

  addNewUser(firstname: string,  lastName: string,  mobileNumber: string,  email: string,
             password: string) {
    var newUser: NewUserModel ={firstName:firstname, lastName:lastName,mobileNumber:mobileNumber,email:email, password:password}
    this.userService.addNewUser(newUser).subscribe( res => {
      console.log(res);
      this.checkResponse(res);
    },
      err => { console.log(err) });
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

  private close(){
    this.dialogRef.close();
  }


}
