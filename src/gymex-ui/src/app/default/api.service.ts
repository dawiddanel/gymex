import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private readonly url = 'http://localhost:8085/gymex'

  public constructor(
    private readonly httpClient: HttpClient
  ) {
  }

  public get<T = any>(endpoint: string): Observable<T> {
    return this.httpClient.get<T>(this.prepareUrl(endpoint));
  }

  public post<TResponse = any, TBody = any>(endpoint: string, body?: TBody): Observable<TResponse> {
    return this.httpClient.post<TResponse>(this.prepareUrl(endpoint), body);
  }

  public put<TResponse = any, TBody = any>(endpoint: string, body?: TBody): Observable<TResponse> {
    return this.httpClient.put<TResponse>(this.prepareUrl(endpoint), body);
  }

  public delete<T = any>(endpoint: string): Observable<T> {
    return this.httpClient.delete<T>(this.prepareUrl(endpoint));
  }

  private prepareUrl(endpoint: string): string {
    return `${this.url}/${endpoint}`;
  }
}
