import {Pipe, PipeTransform} from '@angular/core';
import {Role} from "../models/person.model";

@Pipe({name: 'rolePipe'})
export class RolePipe implements PipeTransform {
  transform(value: Role): string {
    switch (value) {
      case Role.OWNER:
        return "Właściciel"
      case Role.EMPLOYEE:
        return "Pracownik"
      case Role.TRAINER:
        return "Trener"
      case Role.MEMBER:
        return "Klient"
      default:
        return value
    }
  }
}
