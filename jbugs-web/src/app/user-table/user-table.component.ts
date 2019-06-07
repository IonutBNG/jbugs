import {Component, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {
  MatDialog,
  MatDialogConfig,
  MatPaginator,
  MatSlideToggle,
  MatSlideToggleChange,
  MatTableDataSource
} from "@angular/material";
import {UserService} from "../services/user-service/user.service";
import {BackendService} from "../services/backend-service/backend.service";
import {AuthService} from "../services/auth-service/auth.service";
import {Router} from "@angular/router";
import {AddUserComponent} from "../add-user/add-user.component";

import {User} from "../user-model/user-table";
import {UserActivate} from "../user-model/activate-user";
import {UserDeactivate} from "../user-model/deactivate-user";
import {EditUserComponent} from "../edit-user/edit-user.component";
import {PermissionsComponent} from "../permissions/permissions.component";
import {UserEmitterService} from "../services/user-emitter-service/user-emitter.service";
import {EditUserService} from "../services/edit-user-service/edit-user.service";


@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.scss']
})
export class UserTableComponent implements OnInit {


  public displayedColumns: string[] = ['firstName', 'lastName', 'email', 'mobileNumber', 'username', 'counter', 'actions'];

  public users : User[];
  private dialogConfig;

  public dataSource: any;
  loadComponent = false;
  private editedUser: User;

  constructor(private backendService: BackendService,
              private authService: AuthService,
              private userService: UserService,
              private router: Router,
              private dialog: MatDialog,
              private userEmitter: UserEmitterService,
              private editUserService: EditUserService) { }


  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {

    this.getAllUsers();
    this.subscribeEmitter();
    this.dialogConfig = new MatDialogConfig();
  }

  public getAllUsers() {
    this.userService.getAllUsers().subscribe(
      (users) => {
        this.users = users as User[];
        this.dataSource =  new MatTableDataSource<User>(this.users);
        this.dataSource.paginator = this.paginator;
      }
    );
  }

  onActivate(username: string) {

    var newActivateUser: UserActivate = {username: username};
        this.userService.activateUser(newActivateUser).subscribe( res =>
        console.log(res));

    window.location.reload();
  }

  private editUserPopup(component: TemplateRef<EditUserComponent>, firstName: string, lastName: string, email: string,
                mobileNumber: string, userName: string, counter: number) {
    this.loadComponent = true;
    this.dialogConfigSetupUser();
    this.dialog.open(EditUserComponent, this.dialogConfig);
    this.setUser(firstName, lastName, email, mobileNumber, userName, counter);
    this.editUserService.viewEditedUser(this.editedUser);
  }

  private setUser(firstName: string, lastName: string, email: string,
                  mobileNumber: string, userName: string, counter: number) {
    this.editedUser = {
      firstName: firstName,
      lastName: lastName,
      email: email,
      mobileNumber: mobileNumber,
      username: userName,
      counter: counter
    };
  }

  private subscribeEmitter() {
    this.userEmitter.changeEmitter.subscribe(value => {
      if (value == true){
        this.reloadTable()
      }
    });
  }

  private reloadTable(){
    this.getAllUsers();
  }

  edit() {
    alert('Edit');
  }

  // logout(){
  //   localStorage.removeItem('token');
  //   this.router.navigate(['/login']);
  // }

  addUserPopup(){
    this.dialogConfigSetupUser();
    this.dialog.open(AddUserComponent, this.dialogConfig);
  }


  private dialogConfigSetupUser(){
    this.dialogConfig.disableClose= false;
    this.dialogConfig.autoFocus = true;
    this.dialogConfig.width = "40%";
  }



  onChange(username: string, counter: number) {
    //if user is active, deactivate it
    if(counter > 0) {
      this.deactivateUser(username);
      console.log("Deactivating user " + username)
    }
    //if user is deactivated, activate it
    if (counter <= 0) {
      this.activateUser(username);
      console.log("Activating user " + username)
    }
  }

  activateUser(username: string) {

    var newActivateUser: UserActivate = {username: username};
    this.userService.activateUser(newActivateUser).subscribe( res => {
      this.ngOnInit();
    });

  }

  deactivateUser(username: string) {

    var newDeactivateUser: UserDeactivate = {username: username};
    this.userService.deactivateUser(newDeactivateUser).subscribe( res => {
      this.ngOnInit();
    });


  }
}
