import {Pipe, PipeTransform} from '@angular/core';
import {EquipmentType, Purpose} from "../models/equipment.model";

@Pipe({name: 'purposePipe'})
export class PurposePipe implements PipeTransform {
  transform(value: Purpose): string {
    switch (value) {
      case Purpose.CARDIO:
        return "Trening kardio"
      case Purpose.STRENGTH:
        return "Trening siłowy"
      default:
        return value
    }
  }
}
