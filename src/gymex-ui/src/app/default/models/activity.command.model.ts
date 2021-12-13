import {Level} from "./activity.model";

export interface CreateActivity {
  definitionId: number,
  trainerId: number,
  startTime: Date,
  endTime: Date,
  capacity: number
}

export const emptyCreateActivity = (): CreateActivity => ({
  definitionId: null,
  trainerId: null,
  startTime: null,
  endTime: null,
  capacity: null
})

export interface UpdateActivity {
  definitionId: number,
  trainerId: number,
  startTime: Date,
  endTime: Date,
  capacity: number
}

export const emptyUpdateActivity = (): UpdateActivity => ({
  definitionId: null,
  trainerId: null,
  startTime: null,
  endTime: null,
  capacity: null
})

export interface CreateActivityDefinition {
  name: string,
  description: string,
  level: Level
}

export const emptyCreateActivityDefinition = (): CreateActivityDefinition => ({
  name: null,
  description: null,
  level: null
})

export interface UpdateActivityDefinition {
  name: string,
  description: string,
  level: Level
}

export const emptyUpdateActivityDefinition = (): UpdateActivityDefinition => ({
  name: null,
  description: null,
  level: null
})
