import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatTableDataSource} from "@angular/material";

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

  public dataSource = new MatTableDataSource<User>(users);

  constructor() { }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
  }

  edit() {
    alert('Edit');
  }
}
