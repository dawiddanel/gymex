import {Injectable} from '@angular/core';
import {LocalStorageService} from "./local-storage.service";

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  public constructor(
    private readonly localStorageService: LocalStorageService
  ) {
  }

  public hasToken(): boolean {
    return !this.isNullOrEmpty(this.getToken());
  }

  public clearToken(): void {
    this.localStorageService.clear('token');
  }

  public setToken(jwt: string): void {
    this.localStorageService.set('token', jwt);
  }

  public getToken(): string {
    return this.localStorageService.get('token');
  }


  isNullOrEmpty(value: string): boolean {
    return value === null || value === undefined || value.length === 0;
  }
}
