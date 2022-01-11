import { Directive } from '@angular/core';
import { Validator, NG_VALIDATORS, ValidatorFn, FormControl } from '@angular/forms';
import {sameDay} from "../utilities";

@Directive({
  selector: '[pastDate]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useClass: DateFromPastValidator,
      multi: true
    }
  ]
})
export class DateFromPastValidator implements Validator {

  validator: ValidatorFn;
  constructor() {
    this.validator = this.dateFromPastValidator();
  }

  validate(c: FormControl) {
    return this.validator(c);
  }

  dateFromPastValidator(): ValidatorFn {
    return (control: FormControl) => {
      if (control.value != null && control.value !== '') {
        let isValid = new Date(control.value) < new Date()
        if (isValid) {
          return null
        } else {
          return {
            pastDate: { valid: false }
          };
        }
      } else {
        return null;
      }
    };
  }
}
