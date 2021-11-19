import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'countryPipe'})
export class CountryPipe implements PipeTransform {
  transform(value: string): string {
    switch (value) {
      case 'PL': return "Polska"
      default: return value
    }
  }
}
