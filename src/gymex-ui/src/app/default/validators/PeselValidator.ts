import { Directive } from '@angular/core';
import { Validator, NG_VALIDATORS, ValidatorFn, FormControl } from '@angular/forms';
import {sameDay} from "../utilities";

@Directive({
  selector: '[pesel]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useClass: PeselValidator,
      multi: true
    }
  ]
})
export class PeselValidator implements Validator {

  validator: ValidatorFn;
  constructor() {
    this.validator = this.peselValidator();
  }

  validate(c: FormControl) {
    return this.validator(c);
  }

  peselValidator(): ValidatorFn {
    return (control: FormControl) => {
      if (control.value != null && control.value !== '') {
        let isValid = this.validatePesel(control.value)
        if (isValid) {
          return null
        } else {
          return {
            pesel: { valid: false }
          };
        }
      } else {
        return null;
      }
    };
  }

  validatePesel(pesel): boolean {
    var reg = /^[0-9]{11}$/;
    if(reg.test(pesel) == false)
      return false;
    else
    {
      var digits = (""+pesel).split("");
      if ((parseInt(pesel.substring( 4, 6)) > 31)||(parseInt(pesel.substring( 2, 4)) > 12))
        return false;

      var checksum = (1*parseInt(digits[0]) + 3*parseInt(digits[1]) + 7*parseInt(digits[2]) + 9*parseInt(digits[3]) + 1*parseInt(digits[4]) + 3*parseInt(digits[5]) + 7*parseInt(digits[6]) + 9*parseInt(digits[7]) + 1*parseInt(digits[8]) + 3*parseInt(digits[9]))%10;
      if(checksum==0) checksum = 10;
      checksum = 10 - checksum;

      return (parseInt(digits[10])==checksum);
    }
  }
}
