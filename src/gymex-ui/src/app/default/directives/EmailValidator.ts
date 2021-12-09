import { Directive } from '@angular/core';
import { Validator, NG_VALIDATORS, ValidatorFn, FormControl } from '@angular/forms';
import {sameDay} from "../utilities";

@Directive({
  selector: '[email]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useClass: EmailValidator,
      multi: true
    }
  ]
})
export class EmailValidator implements Validator {

  validator: ValidatorFn;
  constructor() {
    this.validator = this.emailValidator();
  }

  validate(c: FormControl) {
    return this.validator(c);
  }

  emailValidator(): ValidatorFn {
    return (control: FormControl) => {
      if (control.value != null && control.value !== '') {
        let isValid = /^[_a-z0-9]+(\.[_a-z0-9]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/.test(control.value);
        if (isValid) {
          return null
        } else {
          return {
            email: { valid: false }
          };
        }
      } else {
        return null;
      }
    };
  }
}
