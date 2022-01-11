import {Attribute, Directive} from '@angular/core';
import { Validator, NG_VALIDATORS, ValidatorFn, FormControl } from '@angular/forms';
import {Subscription} from "rxjs";
import {sameDay} from "../utilities";

@Directive({
  selector: '[dateBiggerThan]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useClass: DateBiggerThanValidator,
      multi: true
    }
  ]
})
export class DateBiggerThanValidator implements Validator {

  constructor(@Attribute('dateBiggerThan') public otherDate: string) { }

  validate(c: FormControl) {

    const OtherDate = c.root.get(this.otherDate);
    const CheckDate = c;

    if (CheckDate.value === null) {
      return null;
    }

    if (OtherDate) {
      const subscription: Subscription = OtherDate.valueChanges.subscribe(() => {
        CheckDate.updateValueAndValidity();
        subscription.unsubscribe();
      });
    }
    const dateValue = new Date(c.value)
    const otherDate = OtherDate ? new Date(OtherDate.value) : null;

    return OtherDate && (dateValue < otherDate || sameDay(dateValue, otherDate)) ? { dateEarlier: true } : null;
  }

}
