import {Component, OnInit} from '@angular/core';
import {ToastsService} from "../../../default/toasts.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ActivityDefinition} from "../../../default/models/activity.model";
import {Trainer} from "../../../default/models/person.model";
import {CreateActivity, emptyCreateActivity} from "../../../default/models/activity.command.model";
import {TimetableService} from "../../timetable.service";
import {ActivityDefinitionService} from "../../activity_definition/activity-definition.service";
import {PersonService} from "../../../person/person.service";

@Component({
  selector: 'app-activity-add',
  templateUrl: './activity-add.component.html',
  styleUrls: ['./activity-add.component.css']
})
export class ActivityAddComponent implements OnInit {

  gymId: number

  definitions?: ActivityDefinition[];
  trainers?: Trainer[];
  activityCommand: CreateActivity = emptyCreateActivity()

  constructor(private timetableService: TimetableService,
              private activityDefinitionService: ActivityDefinitionService,
              private personService: PersonService,
              private toasts: ToastsService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.gymId = this.route.snapshot.params['gymId']
    this.retrieveDefinitions()
    this.retrieveTrainers()
  }

  retrieveDefinitions(): void {
    this.activityDefinitionService.getAllActivityDefinition()
      .subscribe({
        next: value => {
          this.definitions = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu listy definicji aktywności`)
        }
      })
  }

  retrieveTrainers(): void {
    this.personService.getAllTrainer()
      .subscribe({
        next: value => {
          this.trainers = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu listy dostępnych trenerów`)
        }
      })
  }

  saveActivity(): void {
    this.timetableService.createActivity(this.gymId, this.activityCommand)
      .subscribe({
        next: response => {
          this.toasts.showSuccessToast(`Poprawnie dodano aktywność`)
          this.router.navigate([`/timetable/activity/${this.gymId}`]);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy dodawaniu aktywności`)
        }
      })
  }

}
