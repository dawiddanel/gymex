import {Injectable} from '@angular/core';
import {LocalStorageService} from "./local-storage.service";
import {Role, User} from "../default/models/security.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public constructor(
    private readonly localStorageService: LocalStorageService
  ) {
  }

  public hasUser(): boolean {
    return !this.isNullOrEmpty(this.getUser());
  }

  public clearUser(): void {
    this.localStorageService.clear('user');
  }

  public setUser(user: User): void {
    this.localStorageService.set('user', JSON.stringify(user));
  }

  public getUser(): User {
    const json = this.localStorageService.get('user');
    const jsonObj: any = JSON.parse(json);
    return <User>jsonObj
  }

  isNullOrEmpty(value: User): boolean {
    return value === null || value === undefined
  }

  public isOwner(): boolean {
    return this.getUser().role === Role.OWNER
  }

  public isTrainer(): boolean {
    return this.getUser().role === Role.TRAINER
  }

  public isEmployee(): boolean {
    return this.getUser().role === Role.EMPLOYEE
  }

  public isOwnerOrEmployee(): boolean {
    return this.isEmployee() || this.isOwner()
  }
}
