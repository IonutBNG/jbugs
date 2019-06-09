import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {EditUserModel} from "../../user-model/edit-user";
import {User} from "../../user-model/user-table";

@Injectable({
  providedIn: 'root'
})
export class EditUserService {

  private editedUser: User;
  private editedUserSource = new BehaviorSubject(this.editedUser); //holds the value that needs to be shared between components
  currentEditedUser = this.editedUserSource.asObservable();

  constructor() { }


  viewEditedUser(newEditedUser: User) {
    this.editedUserSource.next(newEditedUser);
  }

}
