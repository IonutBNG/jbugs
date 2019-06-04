import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog, MatDialogConfig, MatPaginator, MatTableDataSource} from "@angular/material";
import {UserService} from "../services/user-service/user.service";
import {AuthService} from "../services/auth-service/auth.service";
import {FormControl} from "@angular/forms";
import {UserEmitterService} from "../services/user-emitter-service/user-emitter.service";
import {User} from "../user-model/user-table";
import {EditUserComponent} from "../edit-user/edit-user.component";
import {EditUserService} from "../services/edit-user-service/edit-user.service";
import {UserActivate} from "../user-model/activate-user";

const users: User[] = [];


@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.scss'],
})
export class UserTableComponent implements OnInit {


  public displayedColumns: string[] = ['firstName', 'lastName', 'email', 'mobileNumber', 'username', 'counter', 'actions'];

  public users : User[];


  constructor(private userEmitter: UserEmitterService,
              private authService: AuthService,
              private userService: UserService,
              private dialog: MatDialog,
              private editUserService: EditUserService) { }

   public dataSource: any;
  loadComponent = false;
  private dialogConfig;
  private editedUser: User;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.getAllUsers();
    this.subscribeEmitter();
    this.dialogConfig = new MatDialogConfig();
  }

  onActivate(username: string) {
    var newActivateUser: UserActivate = {username: username};
    this.userService.activateUser(newActivateUser).subscribe( res =>
      console.log(res));

    window.location.reload();
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

  editUserPopup(component: TemplateRef<EditUserComponent>, firstName: string, lastName: string, email: string,
                mobileNumber: string, userName: string, counter: number) {
    this.loadComponent = true;
    this.dialogConfigSetup();
    this.dialog.open(EditUserComponent, this.dialogConfig);
    this.setUser(firstName, lastName, email, mobileNumber, userName, counter);
    this.editUserService.viewEditedUser(this.editedUser);
  }

  edit(users: User[]) {
    alert('Edit');
  }

  private dialogConfigSetup(){
    this.dialogConfig.disableClose= false;
    this.dialogConfig.autoFocus = true;
    this.dialogConfig.width = "50%";
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

}
