import {Pipe, PipeTransform} from '@angular/core';
import {Level} from "../models/activity.model";

@Pipe({name: 'levelPipe'})
export class LevelPipe implements PipeTransform {
  transform(value: Level): string {
    switch (value) {
      case Level.BEGINNER:
        return "Początkujący"
      case Level.INTERMEDIATE:
        return "Średnio zaawansowany"
      case Level.ADVANCED:
        return "Zaawansowany"
      default:
        return value
    }
  }
}
