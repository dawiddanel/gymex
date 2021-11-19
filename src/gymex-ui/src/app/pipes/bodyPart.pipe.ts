import {Pipe, PipeTransform} from '@angular/core';
import {BodyPart} from "../models/equipment.model";

@Pipe({name: 'bodyPartPipe'})
export class BodyPartPipe implements PipeTransform {
  transform(value: BodyPart): string {
    switch (value) {
      case BodyPart.LEGS:
        return "Nogi"
      case BodyPart.BACK:
        return "Plecy"
      case BodyPart.CHEST:
        return "Klatka piersiowa"
      case BodyPart.SHOULDERS:
        return "Barki"
      case BodyPart.BICEPS:
        return "Biceps"
      case BodyPart.TRICEPS:
        return "Triceps"
      case BodyPart.CALVES:
        return "Łydki"
      case BodyPart.FOREARM:
        return "Przedramie"
      case BodyPart.ANY:
        return "Nie dotyczy"
      default:
        return value
    }
  }
}
