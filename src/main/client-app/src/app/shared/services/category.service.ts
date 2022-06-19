import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import ICategory from "src/app/interfaces/category";
import { API_URL } from "src/endpoint-consts";

@Injectable({
  providedIn: "root",
})
export class CategoryService {
  private _endpoint = "/category";

  constructor(private _httpClient: HttpClient) {}

  public GetCategories(): Observable<ICategory[]> {
    return this._httpClient.get<ICategory[]>(API_URL + this._endpoint);
  }

  public GetCategory(id: number): Observable<ICategory> {
    return this._httpClient.get<ICategory>(API_URL + this._endpoint + "/" + id);
  }

  public CreateCategory(data: ICategory): Observable<ICategory> {
    return this._httpClient.post<ICategory>(API_URL + this._endpoint, data);
  }

  public ReviewCategory(isApproved: boolean): Observable<void> {
    return this._httpClient.post<void>(API_URL + this._endpoint, isApproved);
  }

  public UpdateCategory(data: ICategory): Observable<ICategory> {
    return this._httpClient.put<ICategory>(
      API_URL + this._endpoint + "/" + data.id,
      data
    );
  }

  public DeleteCategory(id: number): Observable<void> {
    return this._httpClient.delete<void>(API_URL + this._endpoint + "/" + id);
  }
}
