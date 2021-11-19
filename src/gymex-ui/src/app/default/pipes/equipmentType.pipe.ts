import {Pipe, PipeTransform} from '@angular/core';
import {EquipmentType} from "../models/equipment.model";

@Pipe({name: 'equipmentTypePipe'})
export class EquipmentTypePipe implements PipeTransform {
  transform(value: EquipmentType): string {
    switch (value) {
      case EquipmentType.MACHINE:
        return "Maszyna"
      case EquipmentType.BARBELL:
        return "Sztanga"
      case EquipmentType.DUMBBELL:
        return "Hantel"
      case EquipmentType.KETTLEBELL:
        return "Kettlebell"
      case EquipmentType.WEIGHT:
        return "Ciężar"
      case EquipmentType.OTHER:
        return "Inne"

      default:
        return value
    }
  }
}
