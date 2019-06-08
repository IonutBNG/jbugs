import {Component, OnInit, Optional} from '@angular/core';
import {PermissionsService} from "../services/permissions-service/permissions.service";
import {Role} from "../services/permissions-service/role-model";
import {Permission} from "../services/permissions-service/permission-model";
import {MatDialogRef} from "@angular/material";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";

export interface PermissionType {
  permission: string,
  isChecked: boolean;
}

@Component({
  selector: 'app-permissions',
  templateUrl: './permissions.component.html',
  styleUrls: ['./permissions.component.scss']
})
export class PermissionsComponent implements OnInit {

  constructor(private permissionService: PermissionsService,
              private toast: ToastrService,
              private router: Router,
              @Optional() private dialogRef: MatDialogRef<PermissionsComponent>) { }

  ngOnInit() {
    this.getAllRoles();
  }


  private selectedValuePermission: string;
  private roles: Role[];

  private addedRolesArray: Role[];
  private deletedRolesArray: Role[];


  private displayedColumns: string[] = ['isChecked', 'permission'];
  private permissions: PermissionType[];
  private dataSource: any;


  private getAllRoles() {
    this.permissionService
      .getAllDeletePermissions()
      .subscribe((roles) => {
        this.roles = roles as Role[];
        this.setAddedRoles(this.roles);
      });
    this.permissionService
      .getAllAddPermissions()
      .subscribe((roles) => {
        this.roles = roles as Role[];
        this.setDeletedRoles(this.roles);
      });

  }

  private setAddedRoles(roles: Role[]){
    this.addedRolesArray = roles;
  }

  private setDeletedRoles(roles: Role[]){
    this.deletedRolesArray = roles;
  }


  private changeRoleHandler(event) {
    this.getAllRoles();
    this.permissions = this.findSpecificPermissions(event);
    this.dataSource = this.permissions;
  }

  private findSpecificPermissions(event): PermissionType[]{
    let findPermissions: PermissionType[] = [];
    this.addedRolesArray.forEach(function (permissionType) {
      if (event == permissionType.type)
        permissionType.permissionDtoList.forEach(function(value){
          findPermissions.push({permission: value.type, isChecked: true});
        });
    });

    this.deletedRolesArray.forEach(function (permissionType) {
      if (event == permissionType.type)
        permissionType.permissionDtoList.forEach(function(value){
          findPermissions.push({permission: value.type, isChecked: false});
        });
    });
    return findPermissions;
  }

  private updatePermission(event, permission: string){
    var checked = event.checked;
    this.findRole(checked, this.selectedValuePermission, permission);
  }

  private findRole(checked: boolean, roleValue: string, permValue: string) {
    var perms = this.findPermissions(checked, roleValue, permValue);
    var role: Role[] = [{
      type: this.selectedValuePermission,
      permissionDtoList: perms
    }];
    this.changePermissions(checked, role);
  }

  private findPermissions(checked: boolean, roleValue: string, permValue: string): Permission[]{
    if (checked){
      return this.findPermissionsDelete(roleValue, permValue);
    }
    return this.findPermissionsAdd(roleValue, permValue);
  }

  private findPermissionsDelete(roleValue: string, permValue: string): Permission[]{
    var perms: Permission[] = [];
    this.deletedRolesArray.forEach(function(role){
      if (role.type == roleValue) {
        role.permissionDtoList.forEach(function (perm) {
          if (perm.type == permValue) {
            perms.push(perm);
          }
        })
      }
    });
    return perms;
  }

  private findPermissionsAdd(roleValue: string, permValue: string) {
    var perms: Permission[] = [];
    this.addedRolesArray.forEach(function(role){
      if (role.type == roleValue) {
        role.permissionDtoList.forEach(function (perm) {
          if (perm.type == permValue) {
            perms.push(perm);
          }
        })
      }
    });
    return perms;
  }

  private changePermissions(checked: boolean, role: Role[]) {
    if (checked)
      this.addPermissions(role);
    else
      this.deletePermission(role);
  }

  private addPermissions(role: Role[]) {
    this.permissionService.putAddPermissions(role).subscribe(res => console.log("RES " + res));
  }

  private deletePermission(role: Role[]) {
    this.permissionService.putDeletePermissions(role).subscribe(res => console.log("RES " + res));
  }


  private toastMessageError(){
    this.toast.error("No data selected!", "Error");
  }


  private toastMessageSuccess(){
    this.toast.success("Data changed successfully!", "Success");
  }

}
