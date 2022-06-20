import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "../../../environments/environment";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  observe: 'response' as 'response'
};
const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  static readonly TOKEN_STORAGE_KEY = 'token';
  constructor(private http: HttpClient) { }

  public generateToken(request) {
    return this.http.post(API_URL + "authenticate", request, { responseType: 'text' as 'json' })
  }

  public welcome(token) {
    let tokenStr = 'Bearer ' + token
    const headers = new HttpHeaders().append("Authentication", tokenStr)
    return this.http.get(API_URL + 'ad', { headers, responseType: 'text' as 'json' })
  }

  public saveToken(token) {
    localStorage.setItem(TokenService.TOKEN_STORAGE_KEY, token);
  }

  public getToken(): string {
    return localStorage.getItem(TokenService.TOKEN_STORAGE_KEY);
  }
  // public getResponseHeaders(credentials: Credentials) {
  //   let loginUrl = API_URL + '/login';
  //   return this.http.post(loginUrl, credentials, httpOptions);
  // }
}