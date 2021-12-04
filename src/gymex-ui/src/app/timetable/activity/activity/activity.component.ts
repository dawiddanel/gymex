import {Component, Input, OnInit} from '@angular/core';
import {UserService} from "../../../security/user.service";
import {Activity} from "../../../default/models/activity.model";
import {PersonService} from "../../../person/person.service";
import {Person} from "../../../default/models/person.model";
import {ToastsService} from "../../../default/toasts.service";
import {TimetableService} from "../../timetable.service";

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {

  @Input() activity: Activity
  @Input() gymId: number
  @Input() timetableId: number

  person?: Person
  alreadyHappened: boolean

  constructor(readonly userService: UserService,
              private personService: PersonService,
              private timetableService: TimetableService,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
    this.getPerson()
    this.alreadyHappened = this.activity.startTime < new Date()
  }

  getPerson(): void {
    this.personService.getCurrentPerson()
      .subscribe({
        next: value => {
          this.person = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu danych osobowych`)
        }
      })
  }

  joinActivity(): void {
    this.timetableService.joinActivity(this.gymId, this.timetableId, this.activity.id)
      .subscribe({
        next: value => {
          this.activity = value
          this.toasts.showSuccessToast("Zapisano!")
        },
        error: err => {
          this.toasts.showErrorToast(`Nie udało się dołączyć`)
        }
      }
    )
  }

  resignFromActivity(): void {
    this.timetableService.resignFromActivity(this.gymId, this.timetableId, this.activity.id)
      .subscribe({
          next: value => {
            this.activity = value
            this.toasts.showSuccessToast("Odwołano obecność!")
          },
          error: err => {
            this.toasts.showErrorToast(`Nie udało się wypisać z zajęć`)
          }
        }
      )
  }

  canPersonResignFromActivity(): boolean {
    if (this.userService.isMember()) {
      return this.activity.participants.some(member => member.id === this.person.id);
    } else {
      return false;
    }
  }

  canPersonJoinActivity(): boolean {
    if (this.userService.isMember()) {
      return !this.canPersonResignFromActivity();
    } else {
      return false;
    }
  }

}
