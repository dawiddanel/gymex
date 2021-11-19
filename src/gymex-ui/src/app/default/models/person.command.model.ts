import {Role} from "./person.model";

export interface CreateTechnicalUser {
  username: string
  password: string
  email: string
  role: Role
  firstName: string
  lastName: string
  pesel: string
  birthDate: string
}

export const emptyCreateTechnicalUser = (): CreateTechnicalUser => ({
  username: null,
  password: null,
  email: null,
  role: null,
  firstName: null,
  lastName: null,
  pesel: null,
  birthDate: null
})

export interface CreatePass {
  startDate: Date,
  endDate: Date
}

export const emptyCreatePass = (): CreatePass => ({
  startDate: null,
  endDate: null
})

export interface SignIn {
  username: string,
  password: string
}

export const emptySignIn = (): SignIn => ({
  username: null,
  password: null
})

export interface Register {
  username: string
  password: string
  email: string
  firstName: string
  lastName: string
  pesel: string
  birthDate: string
}

export const emptyRegister = (): Register => ({
  username: null,
  password: null,
  email: null,
  firstName: null,
  lastName: null,
  pesel: null,
  birthDate: null
})
