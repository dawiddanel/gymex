import {Activity} from "./activity.model";

export interface Timetable {
  id: number,
  activities: Activity[],
  updateDate: Date
  startDate: Date
  endDate: Date
}
