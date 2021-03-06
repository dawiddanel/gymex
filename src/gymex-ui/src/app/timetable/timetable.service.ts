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

  getActualTimetable(gymId: any): Observable<Timetable> {
    return this.api.get(`timetable/${gymId}`)
  }

  getTimetable(gymId: any, timetableId: any): Observable<Timetable> {
    return this.api.get(`timetable/${gymId}/${timetableId}`)
  }

  getActivity(gymId: any, timetableId: any, activityId: any): Observable<Activity> {
    return this.api.get(`timetable/${gymId}/${timetableId}/activity/${activityId}`)
  }

  createActivity(gymId: any, timetableId: any, data: CreateActivity): Observable<Activity> {
    return this.api.post(`timetable/${gymId}/${timetableId}/activity`, data)
  }

  updateActivity(gymId: any, timetableId: any, activityId: any, data: UpdateActivity): Observable<Activity> {
    return this.api.put(`timetable/${gymId}/${timetableId}/activity/${activityId}`, data)
  }

  cancelActivity(gymId: any, timetableId: any, activityId: any): Observable<any> {
    return this.api.put(`timetable/${gymId}/${timetableId}/activity/${activityId}/cancel`)
  }

  joinActivity(gymId: any, timetableId: any, activityId: any): Observable<Activity> {
    return this.api.post(`timetable/${gymId}/${timetableId}/activity/${activityId}/join`)
  }

  resignFromActivity(gymId: any, timetableId: any, activityId: any): Observable<Activity> {
    return this.api.post(`timetable/${gymId}/${timetableId}/activity/${activityId}/resign`)
  }

  confirmAttendance(gymId: any, timetableId: any, activityId: any, userId: any): Observable<Activity> {
    return this.api.post(`timetable/${gymId}/${timetableId}/activity/${activityId}/confirm/${userId}`)
  }

  resignAttendance(gymId: any, timetableId: any, activityId: any, userId: any): Observable<Activity> {
    return this.api.post(`timetable/${gymId}/${timetableId}/activity/${activityId}/unconfirm/${userId}`)
  }

  getAllTrainerActivities(): Observable<Activity[]> {
    return this.api.get(`timetable/activity/trainer/all`)
  }

  getAllSpecificTrainerActivities(id: any): Observable<Activity[]> {
    return this.api.get(`timetable/activity/trainer/${id}/all`)
  }

  getAllMemberActivities(): Observable<Activity[]> {
    return this.api.get(`timetable/activity/member/all`)
  }
}
