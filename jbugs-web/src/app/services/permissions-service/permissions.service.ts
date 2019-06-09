import { Injectable } from '@angular/core';
import {BackendService} from "../backend-service/backend.service";
import {Observable} from "rxjs";
import {Role} from "./role-model";

@Injectable({
  providedIn: 'root'
})
export class PermissionsService {

  constructor(private backendService: BackendService) { }

  public getAllDeletePermissions(): Observable<Role[]>{
    return this.backendService.get("/jbugs/jbugs-api/role/delete-permissionsAllowed");
  }

  public getAllAddPermissions() : Observable<Role[]> {
    return this.backendService.get("/jbugs/jbugs-api/role/add-permissionsAllowed");
  }

  public putDeletePermissions(roles: Role[]): Observable<Role[]> {
    return this.backendService.put("/jbugs/jbugs-api/role/delete-permissionsAllowed", roles);
  }

  public putAddPermissions(roles: Role[]): Observable<Role[]> {
    return this.backendService.put("/jbugs/jbugs-api/role/add-permissionsAllowed", roles);
  }
}
