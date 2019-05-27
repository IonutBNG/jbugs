import {Component, OnInit, Output, ViewChild} from '@angular/core';
import {MatPaginator, MatTableDataSource} from "@angular/material";
import {UserService} from "../services/user-service/user.service";
import {UserModel} from "../user-model/user-model";
import {Subscription} from "rxjs";
import {BackendService} from "../services/backend-service/backend.service";
import {AuthService} from "../services/auth-service/auth.service";
import {Router} from "@angular/router";

export interface User {
  firstname: string;
  lastname: string;
  email: string;
  mobilenumber : string;
  username: string
}

const users: User[] = [
];


@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.scss']
})
export class UserTableComponent implements OnInit {


  public displayedColumns: string[] = ['firstName', 'lastName', 'email', 'mobileNumber', 'userName', 'actions'];

  public users : User[];

  constructor(private backendService: BackendService,
              private authService: AuthService,
              private userService: UserService,
              private router: Router) { }

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

  }


  edit() {
    alert('Edit');
  }

  logout(){
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  addUserPopup(){
    this.router.navigate(['/adduser']);
  }

}
