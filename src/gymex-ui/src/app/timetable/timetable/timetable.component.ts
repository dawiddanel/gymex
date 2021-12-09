import {Component, OnInit} from '@angular/core';
import {ToastsService} from "../../default/toasts.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TimetableService} from "../timetable.service";
import {Timetable} from "../../default/models/timetable.model";
import {PersonService} from "../../person/person.service";
import {Member} from "../../default/models/person.model";
import {SecurityUserService} from "../../security/security-user.service";

@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.component.html',
  styleUrls: ['./timetable.component.css']
})
export class TimetableComponent implements OnInit {

  timetable?: Timetable
  member?: Member
  gymId: number
  timetableId: number

  isOldTimetable: boolean

  constructor(private timetableService: TimetableService,
              private personService: PersonService,
              readonly userService: SecurityUserService,
              private toasts: ToastsService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.gymId = this.route.snapshot.params['gymId']
    this.timetableId = this.route.snapshot.params['timetableId']
    this.retrieveTimetable()
    if (this.userService.isMember()) {
      this.getMember()
    }
  }

  retrieveTimetable(): void {
    if (this.timetableId) {
      this.timetableService.getTimetable(this.gymId, this.timetableId)
        .subscribe({
          next: value => {
            this.timetable = value
            this.isOldTimetable = value.endDate > new Date()
          },
          error: err => {
            this.toasts.showErrorToast(`Błąd przy pobieraniu grafiku`)
          }
        })
    }
    this.timetableService.getActualTimetable(this.gymId)
      .subscribe({
        next: value => {
          this.timetable = value
          this.isOldTimetable = value.endDate > new Date()
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu aktualnego grafiku`)
          this.router.navigate(['/assortment/equipmentDefinitions']);

        }
      })
  }

  getMember(): void {
    this.personService.getCurrentMember()
      .subscribe({
        next: value => {
          this.member = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu danych klienta`)
        }
      })
  }

}
