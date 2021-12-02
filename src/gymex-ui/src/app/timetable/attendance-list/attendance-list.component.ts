import { Component, OnInit } from '@angular/core';
import {Activity, emptyActivity} from "../../default/models/activity.model";
import {TimetableService} from "../timetable.service";
import {ActivityDefinitionService} from "../activity_definition/activity-definition.service";
import {PersonService} from "../../person/person.service";
import {ToastsService} from "../../default/toasts.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-attendance-list',
  templateUrl: './attendance-list.component.html',
  styleUrls: ['./attendance-list.component.css']
})
export class AttendanceListComponent implements OnInit {

  gymId: number
  activity: Activity;

  constructor(private timetableService: TimetableService,
              private activityDefinitionService: ActivityDefinitionService,
              private personService: PersonService,
              private toasts: ToastsService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.gymId = this.route.snapshot.params['gymId']
    this.getActivity(this.route.snapshot.params['id'])
  }

  getActivity(id: string): void {
    this.timetableService.getActivity(this.gymId, id)
      .subscribe({
        next: value => {
          this.activity = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu aktywności`)
        }
      })
  }

}
