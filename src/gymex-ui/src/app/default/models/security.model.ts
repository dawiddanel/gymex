export interface SignIn {
  username: string,
  password: string
}

export enum Role {
  OWNER = "OWNER",
  EMPLOYEE = "EMPLOYEE",
  TRAINER = "TRAINER",
  MEMBER = "MEMBER"
}

export const emptySignIn = (): SignIn => ({
  username: null,
  password: null,
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

export interface Authentication {
  token: string
}

export interface User {
  username: string,
  email: string,
  role: Role,
  createdDate: Date
}
