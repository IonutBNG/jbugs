import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import { UserComponent } from './user/user.component';
import { LoginComponent } from './login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UserTableComponent} from "./user-table/user-table.component";
import {MatRadioModule} from '@angular/material/radio';


import {
  MatButtonModule, MatCardModule,
  MatDatepickerModule, MatFormFieldModule,
  MatIconModule,
  MatMenuModule,
  MatNativeDateModule,
  MatTableModule
} from "@angular/material";
import { MatPaginatorModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AuthService} from "./services/auth-service/auth.service";
import {AuthGuard} from "./auth-guard/auth.guard";
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
import { AuthenticationInterceptorComponent } from './authentication-interceptor/authentication-interceptor.component';
import { AddBugComponent } from './add-bug/add-bug.component';

import {MatSelectModule} from '@angular/material/select';
import {MatSortModule} from '@angular/material/sort';
import { StatusChangeComponent } from './status-change/status-change.component';


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
    AuthenticationInterceptorComponent,
    ViewBugComponent,
    AddBugComponent, StatusChangeComponent
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
    MatSelectModule,
    MatSortModule,
    ToastrModule.forRoot({
      timeOut: 4000,
      positionClass: 'toast-top-center',
      preventDuplicates: false
    }),
    RecaptchaModule,
    MatDialogModule,
    MatMenuModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatCardModule,
    MatRadioModule
  ],
  exports: [
    ViewBugComponent
  ],
  providers: [
    AuthService,
    AuthGuard,
    MatDatepickerModule,
    {
      provide : HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptorComponent,
      multi   : true,
    }
  ],
  bootstrap: [AppComponent],
  entryComponents: [AddUserComponent,
    ViewBugComponent,BugTableComponent, StatusChangeComponent, StatusChangeComponent]
})
export class AppModule {
}
