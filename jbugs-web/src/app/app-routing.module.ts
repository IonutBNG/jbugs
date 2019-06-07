import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {UserTableComponent} from "./user-table/user-table.component";
import {AddUserComponent} from "./add-user/add-user.component";
import {AuthGuard} from "./auth-guard/auth.guard";
import {HomeComponent} from "./home/home.component";
import {EditUserComponent} from "./edit-user/edit-user.component";
import {BugTableComponent} from "./bug-table/bug-table.component";
import {PermissionsComponent} from "./permissions/permissions.component";
import {AddBugComponent} from "./add-bug/add-bug.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'home',
    component: HomeComponent,
    // canActivate: [AuthGuard],
    children: [
      {
        path: '',
        redirectTo: 'usertable',
        pathMatch: 'full'
      },
      {
        path: 'usertable',
        component: UserTableComponent,
        children: [
          {
            path: "adduser",
            component: AddUserComponent,
          },
          {
            path:"edituser",
            component:EditUserComponent
          },
          {
            path: "permissions",
            component: PermissionsComponent
          },
        ]
      },
      {
        path: "bugtable",
        component: BugTableComponent,
        children: [
          {
            path: 'addbug',
            component: AddBugComponent
          }
        ]
      },
    ]
  },
  {
    path: "**",
    redirectTo: "/login"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
