import {Component, Input, OnInit} from '@angular/core';
import {TimetableService} from "../../timetable/timetable.service";
import {ToastsService} from "../../default/toasts.service";
import {Trainer} from "../../default/models/person.model";
import {unique} from "../../default/utilities";

@Component({
  selector: 'app-trainer-details',
  templateUrl: './trainer-details.component.html',
  styleUrls: ['./trainer-details.component.css']
})
export class TrainerDetailsComponent implements OnInit {

  @Input() trainer: Trainer
  activities: string[] = []

  constructor(private timetableService: TimetableService,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
    this.getActivities()
  }

  getActivities(): void {
    this.timetableService.getAllSpecificTrainerActivities(this.trainer.id)
      .subscribe({
        next: value => {
          this.activities = value.map(activity => activity.definition.name).filter(unique)
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu aktywności trenera`)
        }
      })
  }

}
