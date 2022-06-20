import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import IAd from "src/app/interfaces/ad";
import { API_URL } from "src/endpoint-consts";

@Injectable({
  providedIn: "root",
})
export class AdService {
  private _endpoint = "/ad";

  constructor(private _httpClient: HttpClient) {}

  public GetAds(): Observable<IAd[]> {
    return this._httpClient.get<IAd[]>(API_URL + this._endpoint);
  }

  public GetAd(id: number): Observable<IAd> {
    return this._httpClient.get<IAd>(API_URL + this._endpoint + "/" + id);
  }

  public CreateAd(data: IAd): Observable<IAd> {
    return this._httpClient.post<IAd>(API_URL + this._endpoint, data);
  }

  public ReviewAd(isApproved: boolean): Observable<void> {
    return this._httpClient.post<void>(API_URL + this._endpoint, isApproved);
  }

  public UpdateAd(data: IAd): Observable<IAd> {
    console.log(API_URL + this._endpoint + "/" + data.id)
    console.log(data)
    return this._httpClient.put<IAd>(API_URL + this._endpoint + "/" + data.id, data);
  }

  public DeleteAd(id: number): Observable<void> {
    return this._httpClient.delete<void>(API_URL + this._endpoint + "/" + id);
  }
}
