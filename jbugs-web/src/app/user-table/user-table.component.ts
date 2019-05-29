import {Component, OnInit, Output, ViewChild} from '@angular/core';
import {MatDialog, MatPaginator, MatSlideToggle, MatSlideToggleChange, MatTableDataSource} from "@angular/material";
import {UserService} from "../services/user-service/user.service";
import {UserModel} from "../user-model/user-model";
import {Subscription} from "rxjs";
import {BackendService} from "../services/backend-service/backend.service";
import {AuthService} from "../services/auth-service/auth.service";
import {Router} from "@angular/router";
import {MatTooltipModule} from '@angular/material/tooltip';
import {AddUserComponent} from "../add-user/add-user.component";
import {FormControl} from "@angular/forms";

export interface User {
  firstname: string;
  lastname: string;
  email: string;
  mobilenumber : string;
  username: string
}

const users: User[] = [];


@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.scss']
})
export class UserTableComponent implements OnInit {


  public displayedColumns: string[] = ['firstName', 'lastName', 'email', 'mobileNumber', 'userName', 'counter', 'actions'];

  public users : User[];
  private dialogConfig;

  constructor(private backendService: BackendService,
              private authService: AuthService,
              private userService: UserService,
              private router: Router,
              private dialog: MatDialog) { }

   public dataSource: any;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {

    this.userService.getAllUsers().subscribe(
      (users) => {
        this.users = users as User[];
        this.dataSource =  new MatTableDataSource<User>(this.users);
        this.dataSource.paginator = this.paginator;
      }
    );

    this.dialogConfig = new MatDialogConfig();
  }



  onActivate(username: string, counter: number) {

    console.log(counter);


      var newActivateUser: UserActivate = {username: username};
          this.userService.activateUser(newActivateUser).subscribe( res =>
          console.log(res));

    window.location.reload();

  }

  edit() {
    alert('Edit');
  }

  logout(){
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  addUserPopup(){
    this.dialogConfigSettup();
    this.dialog.open(AddUserComponent, this.dialogConfig);
  }

  private dialogConfigSettup(){
    this.dialogConfig.disableClose= true;
    this.dialogConfig.autoFocus = true;
    this.dialogConfig.width = "50%";
  }

}
