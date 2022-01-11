import {Directive} from '@angular/core';
import {FormControl, NG_VALIDATORS, Validator, ValidatorFn} from '@angular/forms';

@Directive({
  selector: '[activityStartDate]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useClass: ActivityStartDateValidator,
      multi: true
    }
  ]
})
export class ActivityStartDateValidator implements Validator {

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
        let thresholdDate = new Date()
        thresholdDate.setHours(thresholdDate.getHours() + 1)
        let isValid = new Date(control.value) > thresholdDate
        if (isValid) {
          return null
        } else {
          return {
            activityStartDate: {valid: false}
          };
        }
      } else {
        return null;
      }
    };
  }
}
