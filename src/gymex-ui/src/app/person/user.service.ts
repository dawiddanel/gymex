import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {ApiService} from "../default/api.service";
import {CreateTechnicalUser} from "../default/models/person.command.model";
import {User} from "../default/models/security.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private api: ApiService) {
  }

  getCurrentUser(): Observable<User> {
    return this.api.get(`user`)
  }

  createTechnicalUser(data: CreateTechnicalUser): Observable<User> {
    return this.api.post(`user/technical`, data)
  }

}
