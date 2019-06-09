import {
  Component,
  OnInit,
  Optional,
} from '@angular/core';
import {UserService} from "../services/user-service/user.service";
import {MatDialogRef} from "@angular/material";
import {ToastrService} from "ngx-toastr";
import {UserEmitterService} from "../services/user-emitter-service/user-emitter.service";
import {FormControl} from "@angular/forms";
import {AddUserModel} from "../user-model/add-user-model";
import {PermissionsService} from "../services/permissions-service/permissions.service";
import {Role} from "../services/permissions-service/role-model";


@Component({
  selector: 'add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {


  constructor(private userService: UserService,
              private toast: ToastrService,
              private userEmitter: UserEmitterService,
              private permissionService: PermissionsService,
              @Optional() private dialogRef: MatDialogRef<AddUserComponent>) {

  }

  public addedUserSuccesfully:boolean;
  private rolesControl = new FormControl();
  private selectedRoles: string[];

  private roles: Role[];
  private allRoles: string[];

  ngOnInit() {
    this.getAllRoles();
  }

  private getAllRoles() {
    this.permissionService
      .getAllDeletePermissions()
      .subscribe((roles) => {
        this.roles = roles as Role[];
        this.setRoles(this.roles);
      });
  }

  private setRoles(roles: Role[]){
    let rolesType: string[] = [];
    roles.forEach(function(role){
      rolesType.push(role.type);
    });
    this.allRoles = rolesType;
  }

  private changeRole(event){
    this.selectedRoles = event;
  }

  addNewUser(firstname: string,  lastName: string,  mobileNumber: string,  email: string,
             password: string) {
    var newUser: AddUserModel ={firstName:firstname, lastName:lastName,mobileNumber:mobileNumber,email:email,
      password:password, roles: this.selectedRoles};
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
