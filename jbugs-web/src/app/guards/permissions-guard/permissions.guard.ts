import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {PermsAssignments} from "../../user-model/perms-assignments";
import {AuthService} from "../../services/auth-service/auth.service";

@Injectable({
  providedIn: 'root'
})
export class PermissionsGuard implements CanActivate {

  constructor(private authService: AuthService,
              private router: Router){}

  private permsAssignments: PermsAssignments;

canActivate(): boolean {
  this.permsAssignments = this.authService.setPermissionsRouting();
  if (!this.permsAssignments.isPermsM)
    this.router.navigate(['/home']);
  return true;
}
}
