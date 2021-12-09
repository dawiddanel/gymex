import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Gym, Presence} from "../default/models/gym.model";
import {CreateGym, CreatePresence, UpdateGym} from "../default/models/gym.command.model";
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

  allGymCurrentPresences(id: any): Observable<Presence[]> {
    return this.api.get(`gym/${id}/presence/current`)
  }

  createPresence(id: any, data: CreatePresence): Observable<void> {
    return this.api.post(`gym/${id}/presence`, data)
  }

  finishPresence(id: any, presenceId: any): Observable<void> {
    return this.api.put(`gym/${id}/presence/${presenceId}/finish`)
  }

  getAllMemberPresence(): Observable<Presence[]> {
    return this.api.get(`gym/presence/member/all`)
  }
}
