import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Gym} from "../models/gym.model";
import {CreateGym, UpdateGym} from "../models/gym.command.model";

const baseUrl = 'http://localhost:8085/gymex/gym';

@Injectable({
  providedIn: 'root'
})
export class GymService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Gym[]> {
    return this.http.get<Gym[]>(`${baseUrl}/all`);
  }

  get(id: any): Observable<Gym> {
    return this.http.get<Gym>(`${baseUrl}/${id}`);
  }

  create(data: CreateGym): Observable<Gym> {
    return this.http.post<Gym>(baseUrl, data);
  }

  update(id: any, data: UpdateGym): Observable<Gym> {
    return this.http.put<Gym>(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
}
