import {Component, OnInit, ViewChild} from '@angular/core';
import { MatPaginator, MatTableDataSource} from "@angular/material";
import {UserService} from "../services/user-service/user.service";
import {AuthService} from "../services/auth-service/auth.service";
import {FormControl} from "@angular/forms";
import {UserEmitterService} from "../services/user-emitter-service/user-emitter.service";

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
  styleUrls: ['./user-table.component.scss'],
})
export class UserTableComponent implements OnInit {


  public displayedColumns: string[] = ['firstName', 'lastName', 'email', 'mobileNumber', 'userName', 'actions'];

  public users : User[];

  constructor(private userEmitter: UserEmitterService,
              private authService: AuthService,
              private userService: UserService,) { }

   public dataSource: any;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.getAllUsers();
    this.subscribeEmitter();
  }

  autoRenew = new FormControl();

  onActivate(user: User) {
    alert(user.username);
    console.log(this.autoRenew.value);
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

  edit(users: User[]) {
    alert('Edit');
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
