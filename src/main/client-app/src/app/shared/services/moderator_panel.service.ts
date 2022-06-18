import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import IModeratorPanel from "src/app/interfaces/moderator_panel";
import { API_URL } from "src/endpoint-consts";

@Injectable({
  providedIn: "root",
})
export class ModeratorPanelService {
  private _endpoint = "/moderator_panel";

  constructor(private _httpClient: HttpClient) {}

  public GetAds(): Observable<IModeratorPanel[]> {
    return this._httpClient.get<IModeratorPanel[]>(API_URL + this._endpoint);
  }

  public GetAd(id: number): Observable<IModeratorPanel> {
    return this._httpClient.get<IModeratorPanel>(API_URL + this._endpoint + "/" + id);
  }

  public CreateAd(data: IModeratorPanel): Observable<IModeratorPanel> {
    return this._httpClient.post<IModeratorPanel>(API_URL + this._endpoint, data);
  }

  public ReviewAd(isApproved: boolean): Observable<void> {
    return this._httpClient.post<void>(API_URL + this._endpoint, isApproved);
  }

  public UpdateAd(data: IModeratorPanel): Observable<IModeratorPanel> {
    return this._httpClient.put<IModeratorPanel>(
      API_URL + this._endpoint + "/" + data.id,
      data
    );
  }

  public DeleteAd(id: number): Observable<void> {
    return this._httpClient.delete<void>(API_URL + this._endpoint + "/" + id);
  }
}
