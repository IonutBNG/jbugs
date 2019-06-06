import {Component, Injectable, OnInit} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthService} from "../services/auth-service/auth.service";

@Component({
  selector: 'app-authentication-interceptor',
  templateUrl: './authentication-interceptor.component.html',
  styleUrls: ['./authentication-interceptor.component.scss']
})

@Injectable()
export class AuthenticationInterceptorComponent implements HttpInterceptor {

  constructor() { }

  ngOnInit() {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("Interceptor called");
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ` + localStorage.getItem('token'),
      },
    });

    return next.handle(req);
  }

}
