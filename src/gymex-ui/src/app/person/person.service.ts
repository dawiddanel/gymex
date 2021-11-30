import {Injectable} from "@angular/core";
import {ApiService} from "../default/api.service";
import {Observable} from "rxjs";
import {Employee, Member, Owner, Person, Trainer} from "../default/models/person.model";

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private api: ApiService) {
  }

  getCurrentPerson(): Observable<Person> {
    return this.api.get(`person`)
  }

  getAllMembers(): Observable<Member[]> {
    return this.api.get(`person/member/all`)
  }

  getAllOwners(): Observable<Owner[]> {
    return this.api.get(`person/owner/all`)
  }

  getAllEmployee(): Observable<Employee[]> {
    return this.api.get(`person/employee/all`)
  }

  getAllTrainer(): Observable<Trainer[]> {
    return this.api.get(`person/trainer/all`)
  }

}
