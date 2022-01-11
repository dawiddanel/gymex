import { Directive } from '@angular/core';
import { Validator, NG_VALIDATORS, ValidatorFn, FormControl } from '@angular/forms';
import {sameDay} from "../utilities";

@Directive({
  selector: '[postalCode]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useClass: PostalCodeValidator,
      multi: true
    }
  ]
})
export class PostalCodeValidator implements Validator {

  validator: ValidatorFn;
  constructor() {
    this.validator = this.postalCodeValidator();
  }

  validate(c: FormControl) {
    return this.validator(c);
  }

  postalCodeValidator(): ValidatorFn {
    return (control: FormControl) => {
      if (control.value != null && control.value !== '') {
        let isValid = /^[0-9]{2}-[0-9]{3}$/.test(control.value);
        if (isValid) {
          return null
        } else {
          return {
            postalCode: { valid: false }
          };
        }
      } else {
        return null;
      }
    };
  }
}
