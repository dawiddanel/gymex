export interface Person {
  id: number,
  firstName: string,
  lastName: string,
  pesel: string,
  birthDate: Date
}

export const emptyPerson = (): Person => ({
  id: null,
  firstName: null,
  lastName: null,
  pesel: null,
  birthDate: null
})

export interface Pass {
  startDate: Date,
  endDate: Date
}

export const emptyPass = (): Pass => ({
  startDate: null,
  endDate: null
})

export interface Member extends Person {
  pass: Pass
}

export const emptyMember = (): Member => ({
  id: null,
  firstName: null,
  lastName: null,
  pesel: null,
  birthDate: null,
  pass: emptyPass()
})

export interface Trainer extends Person {
  description: string
}

export const emptyTrainer = (): Trainer => ({
  id: null,
  firstName: null,
  lastName: null,
  pesel: null,
  birthDate: null,
  description: null
})

export interface Employee extends Person {
}

export const emptyEmployee = (): Employee => ({
  id: null,
  firstName: null,
  lastName: null,
  pesel: null,
  birthDate: null
})

export interface Owner extends Person {
}

export const emptyOwner = (): Owner => ({
  id: null,
  firstName: null,
  lastName: null,
  pesel: null,
  birthDate: null
})
