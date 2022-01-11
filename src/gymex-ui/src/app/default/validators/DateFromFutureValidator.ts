import { Directive } from '@angular/core';
import { Validator, NG_VALIDATORS, ValidatorFn, FormControl } from '@angular/forms';
import {sameDay} from "../utilities";

@Directive({
  selector: '[futureDate]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useClass: DateFromFutureValidator,
      multi: true
    }
  ]
})
export class DateFromFutureValidator implements Validator {

  validator: ValidatorFn;
  constructor() {
    this.validator = this.dateFromFutureValidator();
  }

  validate(c: FormControl) {
    return this.validator(c);
  }

  dateFromFutureValidator(): ValidatorFn {
    return (control: FormControl) => {
      if (control.value != null && control.value !== '') {
        let isValid = new Date(control.value) > new Date() || sameDay(new Date(control.value), new Date())
        if (isValid) {
          return null
        } else {
          return {
            futureDate: { valid: false }
          };
        }
      } else {
        return null;
      }
    };
  }
}
