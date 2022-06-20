import { Injectable } from '@angular/core';
import { Router } from "@angular/router";
import { AccountService } from './account.service';
import { TokenService } from "./token.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  static readonly TOKEN_STORAGE_KEY = 'token';
  redirectToUrl: string = '/cookies';

  constructor(private router: Router, private tokenService: TokenService, private accountService: AccountService) { }

  public isAuthenticated(): boolean {
    const token = this.tokenService.getToken();

    if (token) {
      const payload = atob(token.split('.')[1]); // decode payload of token
      const parsedPayload = JSON.parse(payload); // convert payload into an Object

      if (!(parsedPayload.exp > Date.now() / 1000)) { // check if token is expired
        return false;
      }

      return true;
    }
    else return false;
  }
}