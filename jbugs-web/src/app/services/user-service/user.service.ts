import { Injectable } from '@angular/core';
import {BackendService} from "../backend-service/backend.service";
import {Observable} from "rxjs";
import {UserModel} from "../../user-model/user-model";
import {of} from "rxjs/internal/observable/of";
import {NewUserModel} from "../../user-model/new-user-model";
import {EditUserModel} from "../../user-model/edit-user";
import {UserActivate} from "../../user-model/activate-user";
import {User} from "../../user-model/user-table";


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

  public activateUser(activateUser: UserActivate) : Observable<UserActivate>{
    console.log(activateUser.username+" User service ");
      return this.backendService.post("/jbugs/jbugs-api/user/activate-user", activateUser)
  }

  public editUser(editedUser: EditUserModel) : Observable<EditUserModel> {
    return this.backendService.post("/jbugs/jbugs-api/user/edit-user", editedUser);
  }

}
