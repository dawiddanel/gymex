import {Attribute, Directive} from '@angular/core';
import {FormControl, NG_VALIDATORS, Validator} from '@angular/forms';
import {Subscription} from "rxjs";

@Directive({
  selector: '[activityEnd]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useClass: ActivityEndDateValidator,
      multi: true
    }
  ]
})
export class ActivityEndDateValidator implements Validator {

  constructor(@Attribute('activityEnd') public activityStartDate: string) {
  }

  validate(c: FormControl) {

    const ActivityStartDate = c.root.get(this.activityStartDate);
    const ActivityEndDate = c;

    if (ActivityEndDate.value === null) {
      return null;
    }

    if (ActivityStartDate) {
      const subscription: Subscription = ActivityStartDate.valueChanges.subscribe(() => {
        ActivityEndDate.updateValueAndValidity();
        subscription.unsubscribe();
      });
    }
    let activityStartDate = new Date(c.value)
    activityStartDate.setMinutes(activityStartDate.getMinutes() - 30)
    const activityEndDate = ActivityStartDate ? new Date(ActivityStartDate.value) : null;

    return ActivityStartDate && (activityStartDate < activityEndDate) ? {activityEnd: true} : null;
  }

}
