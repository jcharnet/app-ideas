import { Injectable, OnInit  } from '@angular/core';
import { OktaAuthService } from '@okta/okta-angular';


@Injectable()
export class AuthService implements OnInit  {

    _isAuthenticated:boolean;

    constructor (public oktaAuth: OktaAuthService) {

    }

    public isAuthenticated():boolean {

        this.oktaAuth.$authenticationState.subscribe(isAuthenticated => this._isAuthenticated = isAuthenticated);
        return this._isAuthenticated;
    }

    async ngOnInit() {
        this._isAuthenticated = await this.oktaAuth.isAuthenticated();
    }

  public getToken(): string {
    return localStorage.getItem('token');
  }
  
}