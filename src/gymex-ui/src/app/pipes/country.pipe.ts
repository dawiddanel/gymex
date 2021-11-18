import { Pipe, PipeTransform } from '@angular/core';
/*
 * Raise the value exponentially
 * Takes an exponent argument that defaults to 1.
 * Usage:
 *   value | exponentialStrength:exponent
 * Example:
 *   {{ 2 | exponentialStrength:10 }}
 *   formats to: 1024
*/
@Pipe({name: 'countryPipe'})
export class CountryPipe implements PipeTransform {
  transform(value: string): string {
    switch (value) {
      case 'PL': return "Polska"
      default: return value
    }
  }
}
