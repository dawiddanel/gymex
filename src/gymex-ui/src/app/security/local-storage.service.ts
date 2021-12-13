import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  public get(key: string): string {
    return localStorage.getItem(key);
  }

  public set(key: string, value: string): void {
    localStorage.setItem(key, value);
  }

  public clear(key: string): void {
    localStorage.removeItem(key);
  }

  public clearAll(): void {
    localStorage.clear();
  }
}
