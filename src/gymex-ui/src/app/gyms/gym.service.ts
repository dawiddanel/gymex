import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Gym} from "../default/models/gym.model";
import {CreateGym, UpdateGym} from "../default/models/gym.command.model";
import {ApiService} from "../default/api.service";

@Injectable({
  providedIn: 'root'
})
export class GymService {

  constructor(private api: ApiService) {
  }

  getAll(): Observable<Gym[]> {
    return this.api.get('gym/all')
  }

  get(id: any): Observable<Gym> {
    return this.api.get(`gym/${id}`)
  }

  create(data: CreateGym): Observable<Gym> {
    return this.api.post(`gym`, data)
  }

  update(id: any, data: UpdateGym): Observable<Gym> {
    return this.api.put(`gym/${id}`, data)
  }

  delete(id: any): Observable<any> {
    return this.api.delete(`gym/${id}`)
  }
}
