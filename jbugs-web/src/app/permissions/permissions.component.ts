import {Component, OnInit, Optional} from '@angular/core';
import {PermissionsService} from "../services/permissions-service/permissions.service";
import {Role} from "../services/permissions-service/role-model";
import {Permission} from "../services/permissions-service/permission-model";
import {MatDialogRef} from "@angular/material";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-permissions',
  templateUrl: './permissions.component.html',
  styleUrls: ['./permissions.component.scss']
})
export class PermissionsComponent implements OnInit {

  constructor(private permissionService: PermissionsService,
              private toast: ToastrService,
              @Optional() private dialogRef: MatDialogRef<PermissionsComponent>) { }

  ngOnInit() {
    this.getAllDeleteRoles();
    this.getAllAddRoles();
  }

  private deleteRoles: Role[];
  private addRoles: Role[];
  private deletePermissions: Permission[];
  private addPermissions: Permission[];

  private types = ['Add', 'Delete'];
  private selectedRadio = 'Add';
  private permType: string;
  private permissionsDeleted: Permission[];
  private permissionsAdded: Permission[];

  private selectedValuePerm: string;
  private selectedValueRole: string;

  private getAllDeleteRoles(){
    this.permissionService
      .getAllDeletePermissions()
      .subscribe((roles) => {
        this.deleteRoles = roles as Role[];
      });
  }

  private showPermissionDelete(event) {
    const arr: Permission[] = [];
    this.deleteRoles.forEach(function(value){
      if (value.type == event){
        value.permissionDtoList.forEach(function(perm){
          arr.push(perm);
        });
      }
    });
    this.deletePermissions = arr;
    this.permType = event;
  }

  private setPermissionsDelete(event){
    const deleted: Permission[] = [];
    this.deletePermissions.forEach(function(value){
      if (event.includes(value.type)){
        deleted.push(value);
      }
    });
    this.permissionsDeleted = deleted;
  }

  private deletePermission() {
    const newRole: Role = {
      type: this.permType,
      permissionDtoList: this.permissionsDeleted
    };
    const deletedRoles: Role[] = [];
    deletedRoles.push(newRole);
    this.reloadDelete(deletedRoles);
  }

  private reloadDelete(deletedRoles: Role[]) {
    if (this.deletePermissions != undefined && this.deletePermissions.length > 0
      && this.permissionsDeleted != undefined && this.permissionsDeleted.length > 0) {
      this.permissionService.putDeletePermissions(deletedRoles)
        .subscribe(res => console.log(res));
      this.setModelsUndefined();
      this.getAllDeleteRoles();
      this.getAllAddRoles();
      this.toastMessageSuccess();
    } else {
      this.toastMessageError();
    }
  }




  private getAllAddRoles() {
    this.permissionService
      .getAllAddPermissions()
      .subscribe((roles) => {
        this.addRoles = roles as Role[];
      });
  }

  private showPermissionAdd(event) {
    const arr: Permission[] = [];
    this.addRoles.forEach(function(value){
      if (value.type == event){
        value.permissionDtoList.forEach(function(perm){
          arr.push(perm);
        });
      }
    });
    this.addPermissions = arr;
    this.permType = event;
  }

  private setPermissionsAdd(event){
    const added: Permission[] = [];
    this.addPermissions.forEach(function(value){
      if (event.includes(value.type)){
        added.push(value);
      }
    });
    this.permissionsAdded = added;
  }


  private addPermission() {
    const newRole: Role = {
      type: this.permType,
      permissionDtoList: this.permissionsAdded
    };
    const addedRoles: Role[] = [];
    addedRoles.push(newRole);
    this.reloadAdd(addedRoles);
  }

  private reloadAdd(addedRoles: Role[]) {
    if (this.addPermissions!= undefined && this.addPermissions.length > 0
      && this.permissionsAdded != undefined && this.permissionsAdded.length > 0) {
      this.permissionService.putAddPermissions(addedRoles)
        .subscribe(res => console.log(res));
      this.setModelsUndefined();
      this.getAllAddRoles();
      this.getAllDeleteRoles();
      this.toastMessageSuccess();
    } else {
      this.toastMessageError();
    }
  }

  private setModelsUndefined(){
    this.selectedValuePerm = undefined;
    this.selectedValueRole = undefined;
    this.addPermissions = [];
    this.deletePermissions = [];
    this.permissionsDeleted = [];
    this.permissionsAdded = [];
  }

  private radioChange(){
    this.getAllAddRoles();
    this.getAllDeleteRoles();
    this.setModelsUndefined();
  }


  private toastMessageError(){
    this.toast.error("No data selected!", "Error");
  }


  private toastMessageSuccess(){
    this.toast.success("Data changed successfully!", "Success");
  }

}
