export interface Gym {
  id: number,
  name: string,
  capacity: number,
  address: Address,
}

export const emptyGym = (): Gym => ({
  id: null,
  name: null,
  capacity: null,
  address: emptyAddress()
})

export interface Address {
  country: string,
  city: string,
  postalCode: string,
  street: string,
  buildingNumber: string
}

export const emptyAddress = (): Address => ({
  country: null,
  city: null,
  postalCode: null,
  street: null,
  buildingNumber: null
})
