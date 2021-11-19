import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ActivityDefinition} from "../../models/activity.model";
import {CreateActivityDefinition, UpdateActivityDefinition} from "../../models/activity.command.model";

const baseUrl = 'http://localhost:8085/gymex/activity/definition';

@Injectable({
  providedIn: 'root'
})
export class ActivityDefinitionService {

  constructor(private http: HttpClient) {
  }

  getAllActivityDefinition(): Observable<ActivityDefinition[]> {
    return this.http.get<ActivityDefinition[]>(`${baseUrl}/all`);
  }

  getActivityDefinition(id: any): Observable<ActivityDefinition> {
    return this.http.get<ActivityDefinition>(`${baseUrl}/${id}`);
  }

  createActivityDefinition(data: CreateActivityDefinition): Observable<ActivityDefinition> {
    return this.http.post<ActivityDefinition>(`${baseUrl}`, data);
  }

  updateActivityDefinition(id: any, data: UpdateActivityDefinition): Observable<ActivityDefinition> {
    return this.http.put<ActivityDefinition>(`${baseUrl}/${id}`, data);
  }

  deleteActivityDefinition(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
}
