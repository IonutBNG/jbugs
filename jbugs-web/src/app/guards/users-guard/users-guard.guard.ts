import { Injectable } from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthService} from "../../services/auth-service/auth.service";
import {PermsAssignments} from "../../user-model/perms-assignments";

@Injectable({
  providedIn: 'root'
})
export class UsersGuard implements  CanActivate{


  constructor(private authService: AuthService,
              private router: Router){

  }

  private permsAssignments: PermsAssignments;

  canActivate(): boolean {
    this.permsAssignments = this.authService.setPermissionsRouting();
    if (!this.permsAssignments.isUserM)
      this.router.navigate(['/home']);
    return true;
  }
}
