import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import IAccount from 'src/app/interfaces/account';
import { API_URL } from 'src/endpoint-consts';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private _endpoint = "/account";

  constructor(private _httpClient: HttpClient) { }

  public GetCurrentAccount(): Observable<IAccount> {
    return this._httpClient.get<IAccount>(API_URL + this._endpoint + "/current");
  }
}
