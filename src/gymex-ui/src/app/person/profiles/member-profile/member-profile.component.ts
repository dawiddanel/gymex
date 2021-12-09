import {Component, OnInit} from '@angular/core';
import {PersonService} from "../../person.service";
import {ToastsService} from "../../../default/toasts.service";
import {emptyMember, Member} from "../../../default/models/person.model";
import {GymService} from "../../../gyms/gym.service";
import {TimetableService} from "../../../timetable/timetable.service";
import {Activity} from "../../../default/models/activity.model";
import {Presence} from "../../../default/models/gym.model";

@Component({
  selector: 'app-member-profile',
  templateUrl: './member-profile.component.html',
  styleUrls: ['./member-profile.component.css']
})
export class MemberProfileComponent implements OnInit {

  member: Member = emptyMember();
  activities: Activity[]
  presences: Presence[]

  constructor(private personService: PersonService,
              private gymService: GymService,
              private timetableService: TimetableService,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
    this.getMember()
    this.getActivities()
    this.getPresences()
  }

  getMember(): void {
    this.personService.getCurrentMember()
      .subscribe({
        next: value => {
          this.member = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu danych osobowych`)
        }
      })
  }

  getActivities(): void {
    this.timetableService.getAllMemberActivities()
      .subscribe({
        next: value => {
          this.activities = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu aktywności użytkownika`)
        }
      })
  }

  getPresences(): void {
    this.gymService.getAllMemberPresence()
      .subscribe({
        next: value => {
          this.presences = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu wizyt na siłowni użytkownika`)
        }
      })
  }

}
