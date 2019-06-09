import {Component, OnChanges, OnInit} from '@angular/core';
import {AddUserComponent} from "../add-user/add-user.component";
import {MatDialog, MatDialogConfig} from "@angular/material";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth-service/auth.service";
import {PermissionsComponent} from "../permissions/permissions.component";
import {PermsAssignments} from "../user-model/perms-assignments";

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnChanges {

  constructor(private dialog: MatDialog,
              private router: Router,
              private authService: AuthService) { }

  private dialogConfig;
  private permsAssignments: PermsAssignments;

  isBugC: boolean = false;
  isPermsM: boolean = false;
  isBugM: boolean = false;
  isUserM: boolean = false;

  ngOnInit() {
    this.dialogConfig = new MatDialogConfig();

    this.permsAssignments = this.authService.setPermissionsRouting();

    this.isUserM = this.permsAssignments.isUserM;
    this.isBugM = this.permsAssignments.isBugM;
    this.isPermsM = this.permsAssignments.isPermsM;
    this.isBugC = this.permsAssignments.isBugC;
  }

  ngOnChanges(): void {

  }
}
