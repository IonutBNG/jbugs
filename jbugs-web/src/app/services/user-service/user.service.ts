import { Injectable } from '@angular/core';
import {BackendService} from "../backend-service/backend.service";
import {Observable} from "rxjs";
import {NewUserModel} from "../../user-model/new-user-model";
import {User} from "../../user-table/user-table.component";
import {EditUserModel} from "../../user-model/edit-user";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private backendService: BackendService) { }


  public getAllUsers(): Observable<User[]> {
    return this.backendService.get("/jbugs/jbugs-api/user/users");
  }

  public addNewUser(newUser: NewUserModel) : Observable<NewUserModel> {
    return this.backendService.post("/jbugs/jbugs-api/user/add-new-user", newUser)
  }

  public editUser(editedUser: EditUserModel) : Observable<EditUserModel> {
    return this.backendService.post("/jbugs/jbugs-api/user/edit-user", editedUser);
  }

}
