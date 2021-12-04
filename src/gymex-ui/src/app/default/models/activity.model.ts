import {Member, Trainer} from "./person.model";

export interface Activity {
  id: number,
  definition: ActivityDefinition,
  trainer: Trainer,
  participants: Member[],
  attendance: Attendance[],
  startTime: Date,
  endTime: Date,
  capacity: number,
  status: ActivityStatus
}

export const emptyActivity = (): Activity => ({
  id: null,
  definition: null,
  trainer: null,
  participants: [],
  attendance: [],
  startTime: null,
  endTime: null,
  capacity: null,
  status: null
})

export interface Attendance {
  member: Member,
  attended: boolean
}

export const emptyAttendance = (): Attendance => ({
  member: null,
  attended: null
})

export interface ActivityDefinition {
  id: number,
  name: string,
  description: string,
  level: Level
}

export const emptyActivityDefinition = (): ActivityDefinition => ({
  id: null,
  name: null,
  description: null,
  level: null
})

export enum Level {
  BEGINNER = "BEGINNER",
  INTERMEDIATE = "INTERMEDIATE",
  ADVANCED = "ADVANCED"
}

export enum ActivityStatus {
  CREATED = "CREATED",
  IN_PROGRESS = "IN_PROGRESS",
  CANCELLED = "CANCELLED",
  FINISHED = "FINISHED"
}
