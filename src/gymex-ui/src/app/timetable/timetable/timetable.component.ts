import {Component, OnInit} from '@angular/core';
import {ToastsService} from "../../default/toasts.service";
import {ActivatedRoute} from "@angular/router";
import {TimetableService} from "../timetable.service";
import {Timetable} from "../../default/models/timetable.model";

@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.component.html',
  styleUrls: ['./timetable.component.css']
})
export class TimetableComponent implements OnInit {

  timetable?: Timetable
  gymId: number

  constructor(private timetableService: TimetableService,
              private toasts: ToastsService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.gymId = this.route.snapshot.params['gymId']
    this.retrieveTimetable()
  }

  retrieveTimetable(): void {
    this.timetableService.getTimetable(this.gymId)
      .subscribe({
        next: value => {
          this.timetable = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu grafiku`)
        }
      })
  }

}
