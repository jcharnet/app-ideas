import { Injectable } from '@angular/core';
import {
  HttpErrorResponse,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { OktaAuthService } from '@okta/okta-angular';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from "rxjs/operators";
import { Router } from '@angular/router';


@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(public oktaAuthService: OktaAuthService, public router: Router) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.oktaAuthService.getAccessToken()}`
      }
    });

    return next.handle(request).pipe(
      map((event: HttpEvent<any>) => {
        return event;
      }),
      catchError(
        (
          httpErrorResponse: HttpErrorResponse,
          _: Observable<HttpEvent<any>>
        ) => {
          if (httpErrorResponse.status === 401) {
            console.log('REDIRECTING TO LOGIN')
            this.router.navigate(['/login']);
          } else {
            if (httpErrorResponse.status !== 0) {
              return throwError(httpErrorResponse.error);  
            } else { 
              return throwError(httpErrorResponse);
            }
          }
        }
      )
    );

  }
}