import {Component, Injectable} from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";
import 'rxjs/add/operator/do';
import {Observable} from "rxjs";

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
    // console.log("Interceptor called");
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ` + localStorage.getItem('token'),
      },
    });

    return next.handle(req).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
      }
    }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401) {
          console.log("Unauthorized: 401");
          console.log(err);
        }
      }
    });
  }

}
