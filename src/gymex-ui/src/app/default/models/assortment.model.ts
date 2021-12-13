import {Equipment} from "./equipment.model";

export interface Assortment {
  id: number,
  equipment: Equipment[],
  updateDate: Date
}
