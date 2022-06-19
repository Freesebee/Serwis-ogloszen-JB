import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {TokenService} from "./token.service";
import {HttpResponse} from "@angular/common/http";
import {Credentials} from "../../login/credentials";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  static readonly TOKEN_STORAGE_KEY = 'token';
  redirectToUrl: string = '/cookies';

  constructor(private router: Router, private tokenService: TokenService) { }
}