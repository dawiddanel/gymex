import {Injectable} from "@angular/core";
import {ApiService} from "../default/api.service";
import {Observable} from "rxjs";
import {Employee, Member, Owner, Pass, Person, Trainer} from "../default/models/person.model";
import {CreatePass, CreateTechnicalUser} from "../default/models/person.command.model";
import {User} from "../default/models/security.model";

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private api: ApiService) {
  }

  getCurrentPerson(): Observable<Person> {
    return this.api.get(`person`)
  }

  getCurrentMember(): Observable<Member> {
    return this.api.get(`person`)
  }

  getCurrentTrainer(): Observable<Trainer> {
    return this.api.get(`person`)
  }

  getCurrentOwner(): Observable<Owner> {
    return this.api.get(`person`)
  }

  getCurrentEmployee(): Observable<Employee> {
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

  buyPass(data: CreatePass): Observable<Pass> {
    return this.api.post(`person/member/pass`, data)
  }

}
