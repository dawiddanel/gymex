export interface CreateGym {
  name: string,
  capacity: number,
  address: CreateAddress,
  timetableStartDate: Date
}

export interface UpdateGym {
  name: string,
  capacity: number,
  address: CreateAddress
}

export const emptyUpdateGym = (): UpdateGym => ({
  name: null,
  capacity: null,
  address: emptyCreateAddress()
})

export const emptyCreateGym = (): CreateGym => ({
  name: null,
  capacity: null,
  address: emptyCreateAddress(),
  timetableStartDate: null
})

export interface CreateAddress {
  country: string,
  city: string,
  postalCode: string,
  street: string,
  buildingNumber: string
}

export const emptyCreateAddress = (): CreateAddress => ({
  country: null,
  city: null,
  postalCode: null,
  street: null,
  buildingNumber: null
})
