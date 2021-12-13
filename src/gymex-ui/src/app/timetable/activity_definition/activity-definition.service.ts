import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ActivityDefinition} from "../../default/models/activity.model";
import {CreateActivityDefinition, UpdateActivityDefinition} from "../../default/models/activity.command.model";
import {ApiService} from "../../default/api.service";

@Injectable({
  providedIn: 'root'
})
export class ActivityDefinitionService {

  constructor(private api: ApiService) {
  }

  getAllActivityDefinition(): Observable<ActivityDefinition[]> {
    return this.api.get('activity/definition/all')
  }

  getActivityDefinition(id: any): Observable<ActivityDefinition> {
    return this.api.get(`activity/definition/${id}`)
  }

  createActivityDefinition(data: CreateActivityDefinition): Observable<ActivityDefinition> {
    return this.api.post(`activity/definition`, data)
  }

  updateActivityDefinition(id: any, data: UpdateActivityDefinition): Observable<ActivityDefinition> {
    return this.api.put(`activity/definition/${id}`, data)
  }

  deleteActivityDefinition(id: any): Observable<any> {
    return this.api.delete(`activity/definition/${id}`)
  }
}
