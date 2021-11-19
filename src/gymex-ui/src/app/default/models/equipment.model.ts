export interface Equipment {
  id: number,
  definition: EquipmentDefinition,
  quantity: number,
}

export const emptyEquipment = (): Equipment => ({
  id: null,
  definition: null,
  quantity: null
})

export interface CreateEquipment {
  definition: EquipmentDefinition,
  quantity: number
}

export const emptyCreateEquipment = (): CreateEquipment => ({
  definition: null,
  quantity: null
})

export interface UpdateEquipment {
  quantity: number
}

export const emptyUpdateEquipment = (): UpdateEquipment => ({
  quantity: null
})

export interface EquipmentDefinition {
  id: number,
  name: string,
  description: string,
  type: EquipmentType,
  purpose: Purpose,
  weight: number,
  aimedBodyParts: BodyPart[],
}

export const emptyEquipmentDefinition = (): EquipmentDefinition => ({
  id: null,
  name: null,
  description: null,
  type: null,
  purpose: null,
  weight: null,
  aimedBodyParts: [],
})

export interface CreateEquipmentDefinition {
  name: string,
  description: string,
  type: EquipmentType,
  purpose: Purpose,
  weight: number,
  aimedBodyParts: BodyPart[],
}

export const emptyCreateEquipmentDefinition = (): CreateEquipmentDefinition => ({
  name: null,
  description: null,
  type: null,
  purpose: null,
  weight: null,
  aimedBodyParts: [],
})

export interface UpdateEquipmentDefinition {
  name: string,
  description: string,
  type: EquipmentType,
  purpose: Purpose,
  weight: number,
  aimedBodyParts: BodyPart[],
}

export const emptyUpdateEquipmentDefinition = (): UpdateEquipmentDefinition => ({
  name: null,
  description: null,
  type: null,
  purpose: null,
  weight: null,
  aimedBodyParts: [],
})

export enum EquipmentType {
  MACHINE = "MACHINE",
  BARBELL = "BARBELL",
  DUMBBELL = "DUMBBELL",
  KETTLEBELL = "KETTLEBELL",
  WEIGHT = "WEIGHT",
  OTHER = "OTHER"
}

export enum Purpose {
  CARDIO = "CARDIO",
  STRENGTH = "STRENGTH"
}

export enum BodyPart {
  LEGS = "LEGS",
  BACK = "BACK",
  CHEST = "CHEST",
  SHOULDERS = "SHOULDERS",
  BICEPS = "BICEPS",
  TRICEPS = "TRICEPS",
  CALVES = "CALVES",
  FOREARM = "FOREARM",
  ANY = "ANY"
}
