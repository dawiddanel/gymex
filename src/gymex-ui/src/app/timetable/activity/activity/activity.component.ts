import {Component, Input, OnInit} from '@angular/core';
import {SecurityUserService} from "../../../security/security-user.service";
import {Activity, ActivityStatus} from "../../../default/models/activity.model";
import {Member} from "../../../default/models/person.model";
import {ToastsService} from "../../../default/toasts.service";
import {TimetableService} from "../../timetable.service";

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {

  @Input() activity: Activity
  @Input() member: Member
  @Input() gymId: number
  @Input() timetableId: number

  status = ActivityStatus
  color: string

  constructor(readonly userService: SecurityUserService,
              private timetableService: TimetableService,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
    switch (this.activity.status) {
      case ActivityStatus.CREATED:
        this.color = 'white'
        break;
      case ActivityStatus.IN_PROGRESS:
        this.color = 'cornflowerblue'
        break;
      case ActivityStatus.CANCELLED:
        this.color = 'sienna'
        break;
      case ActivityStatus.FINISHED:
        this.color = 'lightgrey'
        break;
    }
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
    if (this.member && this.member.pass) {
      return this.activity.participants.some(participant => participant.id === this.member.id);
    } else {
      return false;
    }
  }

  canPersonJoinActivity(): boolean {
    if (this.member && this.member.pass) {
      return !this.canPersonResignFromActivity();
    } else {
      return false;
    }
  }

}
