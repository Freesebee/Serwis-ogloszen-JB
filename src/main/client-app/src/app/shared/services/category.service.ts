import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface ICategory {
  //TODO: Match Dto from API
}

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private _httpClient: HttpClient) { }

  apiUrl = 'hindu-findu.com'; //TODO: Set valid url

  public GetCategories(): Observable<ICategory[]> {
    return this._httpClient.get<ICategory[]>(this.apiUrl + '/category'); //TODO: Set valid url
  }

  public GetCategory(id: number): Observable<ICategory> {
    return this._httpClient.get<ICategory>(this.apiUrl + '/category/' + id) //TODO: Set valid url
  }

  public CreateCategory(data: ICategory): Observable<ICategory> {
    return this._httpClient.post<ICategory>(this.apiUrl + '/category', data) //TODO: Set valid url
  }

  public UpdateCategory(data: ICategory): Observable<ICategory> {
    return this._httpClient.put<ICategory>(this.apiUrl + '/category', data) //TODO: Set valid url
  }

  public DeleteCategory(id: number): Observable<void> {
    return this._httpClient.delete<void>(this.apiUrl + '/category/' + id) //TODO: Set valid url
  }
}
