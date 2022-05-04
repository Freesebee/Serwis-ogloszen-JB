import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ICategory } from './category.service';

export interface IPost {
  //TODO: Match Dto from API
}

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private _httpClient: HttpClient) { }

  apiUrl = 'hindu-findu.com'; //TODO: Set valid url

  public GetPosts(category?: ICategory): Observable<IPost[]> {
    return this._httpClient.get<IPost[]>(this.apiUrl + '/post', category); //TODO: Set valid url
  }

  public GetPost(id: number): Observable<IPost> {
    return this._httpClient.get<IPost>(this.apiUrl + '/post/' + id) //TODO: Set valid url
  }

  public CreateDraftPost(data: IPost): Observable<IPost> {
    return this._httpClient.post<IPost>(this.apiUrl + '/post', data) //TODO: Set valid url
  }
  
  public ReviewPost(approved: boolean): Observable<void> {
    return this._httpClient.post<void>(this.apiUrl + '/post', approved) //TODO: Set valid url
  }

  public UpdatePost(data: IPost): Observable<IPost> {
    return this._httpClient.put<IPost>(this.apiUrl + '/post', data) //TODO: Set valid url
  }

  public DeletePost(id: number): Observable<void> {
    return this._httpClient.delete<void>(this.apiUrl + '/post/' + id) //TODO: Set valid url
  }
}
