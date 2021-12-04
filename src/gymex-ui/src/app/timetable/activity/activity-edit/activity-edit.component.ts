import {Component, OnInit} from '@angular/core';
import {ToastsService} from "../../../default/toasts.service";
import {ActivatedRoute, Router} from "@angular/router";
import Swal from 'sweetalert2/dist/sweetalert2.js';
import {Activity, ActivityDefinition, emptyActivity} from "../../../default/models/activity.model";
import {TimetableService} from "../../timetable.service";
import {PersonService} from "../../../person/person.service";
import {UpdateActivity} from "../../../default/models/activity.command.model";
import {Trainer} from "../../../default/models/person.model";
import {ActivityDefinitionService} from "../../activity_definition/activity-definition.service";

@Component({
  selector: 'app-activity-edit',
  templateUrl: './activity-edit.component.html',
  styleUrls: ['./activity-edit.component.css']
})
export class ActivityEditComponent implements OnInit {

  gymId: number
  timetableId: number

  definitions?: ActivityDefinition[];
  trainers?: Trainer[];
  activity: Activity = emptyActivity()

  constructor(private timetableService: TimetableService,
              private activityDefinitionService: ActivityDefinitionService,
              private personService: PersonService,
              private toasts: ToastsService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.gymId = this.route.snapshot.params['gymId']
    this.timetableId = this.route.snapshot.params['timetableId']
    this.getActivity(this.route.snapshot.params['id'])
    this.retrieveDefinitions()
    this.retrieveTrainers()
  }

  getActivity(id: string): void {
    this.timetableService.getActivity(this.gymId, this.timetableId, id)
      .subscribe({
        next: value => {
          this.activity = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu aktywności`)
        }
      })
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

  updateActivity(): void {
    const data: UpdateActivity = {
      capacity: this.activity.capacity,
      definitionId: this.activity.definition.id,
      endTime: this.activity.endTime,
      startTime: this.activity.startTime,
      trainerId: this.activity.trainer.id
    };

    this.timetableService.updateActivity(this.gymId, this.timetableId, this.activity.id, data)
      .subscribe({
        next: value => {
          this.toasts.showSuccessToast(`Zaktualizowano aktywność`)
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy aktualizacji aktywności`)
        }
      })
  }

  delete() {
    Swal.fire({
      title: "Usuwanie aktywności",
      text: "Czy napewno chcesz usunąć aktywność?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Tak',
      cancelButtonText: 'Nie'
    }).then((result) => {
      if (result.value) {
        this.deleteActivity()
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        this.toasts.showCancelledToast("Zrezygnowano z usuwania")
      }
    })
  }

  deleteActivity(): void {
    this.timetableService.deleteActivity(this.gymId, this.timetableId, this.activity.id)
      .subscribe({
        next: value => {
          this.toasts.showSuccessToast("Pomyślnie usunięto aktywność")
          this.router.navigate([`/timetable/activity/${this.gymId}/${this.timetableId}`]);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy usuwaniu aktywności`)
        }
      })
  }

}
