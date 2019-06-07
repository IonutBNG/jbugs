import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import { UserComponent } from './user/user.component';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UserTableComponent} from "./user-table/user-table.component";

import {
  MatButtonModule, MatButtonToggleModule,
  MatDatepickerModule,
  MatIconModule,
  MatMenuModule, MatSelectModule,
  MatSidenavModule,
  MatTableModule
} from "@angular/material";
import { MatPaginatorModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AuthService} from "./services/auth-service/auth.service";
import {AuthGuard} from "./guards/auth-guard/auth.guard";
import {ToastrModule} from "ngx-toastr";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AddUserComponent} from "./add-user/add-user.component";
import {RecaptchaModule} from "ng-recaptcha";
import {MatDialogModule} from '@angular/material/dialog';

import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {EditUserComponent} from "./edit-user/edit-user.component";
import { BugTableComponent } from './bug-table/bug-table.component';
import { ViewBugComponent } from './view-bug/view-bug.component';

import {MatInputModule} from '@angular/material/input';
import {AuthenticationInterceptorComponent} from "./authentication-interceptor/authentication-interceptor.component";
import {PermissionsComponent} from "./permissions/permissions.component";
import {HomeComponent} from "./home/home.component";
import {AddBugComponent} from "./add-bug/add-bug.component";

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    LoginComponent,
    UserTableComponent,
    AddUserComponent,
    EditUserComponent,
    BugTableComponent,
    ViewBugComponent,
    PermissionsComponent,
    HomeComponent,
    AuthenticationInterceptorComponent,
    AddBugComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatButtonModule,
    MatSlideToggleModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatInputModule,
    ToastrModule.forRoot({
      timeOut: 4000,
      positionClass: 'toast-top-center',
      preventDuplicates: false
    }),
    RecaptchaModule,
    MatDialogModule,
    MatMenuModule,
    MatSidenavModule,
    MatSelectModule,
    MatButtonToggleModule,
    MatDatepickerModule,
    NgbModule.forRoot()
  ],
  exports: [
    ViewBugComponent,
  ],
  providers: [AuthService, AuthGuard,
    MatDatepickerModule,
    {
      provide : HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptorComponent,
      multi   : true,
    }
    ],
  bootstrap: [AppComponent],
  entryComponents: [AddUserComponent,
    ViewBugComponent,BugTableComponent, AddBugComponent
  ]
})
export class AppModule {
}
