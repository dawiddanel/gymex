import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ApiService} from "../default/api.service";
import {Timetable} from "../default/models/timetable.model";
import {Activity} from "../default/models/activity.model";
import {CreateActivity, UpdateActivity} from "../default/models/activity.command.model";

@Injectable({
  providedIn: 'root'
})
export class TimetableService {

  constructor(private api: ApiService) {
  }

  getTimetable(gymId: any): Observable<Timetable> {
    return this.api.get(`timetable/${gymId}`)
  }

  getActivity(gymId: any, activityId: any): Observable<Activity> {
    return this.api.get(`timetable/${gymId}/activity/${activityId}`)
  }

  createActivity(gymId: any, data: CreateActivity): Observable<Activity> {
    return this.api.post(`timetable/${gymId}/activity`, data)
  }

  updateActivity(gymId: any, id: any, data: UpdateActivity): Observable<Activity> {
    return this.api.put(`timetable/${gymId}/activity/${id}`, data)
  }

  deleteActivity(gymId: any, id: any): Observable<any> {
    return this.api.delete(`timetable/${gymId}/activity/${id}`)
  }

  joinActivity(gymId: any, activityId: any): Observable<Activity> {
    return this.api.post(`timetable/${gymId}/activity/${activityId}/join`)
  }

  resignFromActivity(gymId: any, activityId: any): Observable<Activity> {
    return this.api.post(`timetable/${gymId}/activity/${activityId}/resign`)
  }

  confirmAttendance(gymId: any, activityId: any, userId: any): Observable<Activity> {
    return this.api.post(`timetable/${gymId}/activity/${activityId}/confirm/${userId}`)
  }

  resignAttendance(gymId: any, activityId: any, userId: any): Observable<Activity> {
    return this.api.post(`timetable/${gymId}/activity/${activityId}/unconfirm/${userId}`)
  }
}
