import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { OktaAuthService } from '@okta/okta-angular';
import { Observable, throwError } from 'rxjs';
import { Bin2Dec } from './bin2dec.model'
import config from '../../app.config';
import { catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class Bin2DecService {

  accessToken: string;

  constructor(private oktaAuth: OktaAuthService, private http: HttpClient) { 
    this.accessToken = this.oktaAuth.getAccessToken();
  }

  convertBinaryToDecimal(_binaryNumber: string): Observable<Bin2Dec> {

    return this.http.get<Bin2Dec>(config.resourceServer.bin2decUrl, {
      /*headers: {
        Authorization: 'Bearer ' + this.accessToken,
      },*/
      params: {
        binaryNumber: _binaryNumber,
      }
    }).pipe(catchError(this.handleError));
  }
  
  private handleError(res: HttpErrorResponse) {
    console.error("Error: ", res.error);
    return throwError(res.error || 'Server Error')
  }
}


