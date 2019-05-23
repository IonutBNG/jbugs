import { Injectable } from '@angular/core';
import {BackendService} from "../backend-service/backend.service";
import {Observable} from "rxjs";
import {UserModel} from "../../user-model/user-model";
import {of} from "rxjs/internal/observable/of";
import {NewUserModel} from "../../user-model/new-user-model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private backendService: BackendService) { }

  public getUser(): Observable<UserModel>{
    // return this.backendService.post("/jbugs/jbugs-api/user/authenticate", );
    return of({username: "user", password: "pass"});
  }

  public loginUser(user: UserModel) : Observable<UserModel>{
    // const u: UserModel = {username: "AdminMock", password: "PasswordMock"};
    return this.backendService.post("/jbugs/jbugs-api/user/authenticate", user);
  }

  public addNewUser(newUser: NewUserModel) : Observable<NewUserModel> {
    console.log(newUser);
    return this.backendService.post("/jbugs/jbugs-api/user/add-new-user", newUser)
  }
}