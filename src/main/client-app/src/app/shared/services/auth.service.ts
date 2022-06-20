import { Injectable } from '@angular/core';
import { Router } from "@angular/router";
import { TokenService } from "./token.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  static readonly TOKEN_STORAGE_KEY = 'token';
  redirectToUrl: string = '/cookies';

  constructor(private router: Router, private tokenService: TokenService) { }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token'); // get token from local storage
    const payload = atob(token.split('.')[1]); // decode payload of token
    const parsedPayload = JSON.parse(payload); // convert payload into an Object

    return parsedPayload.exp > Date.now() / 1000; // check if token is expired
  }
}