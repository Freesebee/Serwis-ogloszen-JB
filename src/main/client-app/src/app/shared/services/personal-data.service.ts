import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import IAccount from 'src/app/interfaces/account';
import { API_URL } from 'src/endpoint-consts';
import IAd from "../../interfaces/ad";
import IPersonalData from "../../interfaces/personal-data";

@Injectable({
  providedIn: 'root'
})
export class PersonalDataService {
  private _endpoint = "/personal-data";

  constructor(private _httpClient: HttpClient) { }

  public GetCurrentPersonalData(): Observable<IPersonalData> {
    return this._httpClient.get<IPersonalData>(API_URL + this._endpoint + "/current");
  }

  public CreatePersonalData(data: IPersonalData): Observable<IPersonalData> {
    return this._httpClient.post<IPersonalData>(API_URL + this._endpoint, data);
  }
}
