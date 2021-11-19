import {Member, Trainer} from "./person.model";

export interface Activity {
  id: number,
  definition: ActivityDefinition,
  trainer: Trainer,
  participants: Member[],
  attendance: Attendance[],
  startTime: Date,
  duration: number,
  capacity: number
}

export const emptyActivity = (): Activity => ({
  id: null,
  definition: null,
  trainer: null,
  participants: [],
  attendance: [],
  startTime: null,
  duration: null,
  capacity: null
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
