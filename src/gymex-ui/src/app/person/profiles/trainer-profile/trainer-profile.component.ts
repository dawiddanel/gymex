import { Component, OnInit } from '@angular/core';
import {emptyMember, emptyTrainer, Member, Trainer} from "../../../default/models/person.model";
import {PersonService} from "../../person.service";
import {ToastsService} from "../../../default/toasts.service";
import {Activity} from "../../../default/models/activity.model";
import {TimetableService} from "../../../timetable/timetable.service";

@Component({
  selector: 'app-trainer-profile',
  templateUrl: './trainer-profile.component.html',
  styleUrls: ['./trainer-profile.component.css']
})
export class TrainerProfileComponent implements OnInit {

  trainer: Trainer = emptyTrainer();
  activities: Activity[]

  constructor(private personService: PersonService,
              private timetableService: TimetableService,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
    this.getTrainer();
    this.getActivities()
  }

  getTrainer(): void {
    this.personService.getCurrentTrainer()
      .subscribe({
        next: value => {
          this.trainer = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu danych osobowych`)
        }
      })
  }

  getActivities(): void {
    this.timetableService.getAllTrainerActivities()
      .subscribe({
        next: value => {
          this.activities = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu aktywności trenera`)
        }
      })
  }

}
