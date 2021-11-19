import {Component, OnInit} from '@angular/core';
import {enumSelector} from "../../../default/utilities";
import {ActivityDefinition, emptyActivityDefinition, Level} from "../../../models/activity.model";
import {ActivityDefinitionService} from "../activity-definition.service";
import {ToastsService} from "../../../default/toasts.service";
import {ActivatedRoute, Router} from "@angular/router";
import Swal from 'sweetalert2/dist/sweetalert2.js';
import {UpdateActivityDefinition} from "../../../models/activity.command.model";

@Component({
  selector: 'app-activity-definition-edit',
  templateUrl: './activity-definition-edit.component.html',
  styleUrls: ['./activity-definition-edit.component.css']
})
export class ActivityDefinitionEditComponent implements OnInit {

  definition: ActivityDefinition = emptyActivityDefinition()
  levels = enumSelector(Level)

  constructor(private activityDefinitionService: ActivityDefinitionService,
              private toasts: ToastsService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getDefinition(this.route.snapshot.params['id']);
  }

  getDefinition(id: string): void {
    this.activityDefinitionService.getActivityDefinition(id)
      .subscribe({
        next: value => {
          this.definition = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu definicji`)
        }
      })
  }

  updateDefinition(): void {
    const data: UpdateActivityDefinition = {
      name: this.definition.name,
      description: this.definition.description,
      level: this.definition.level
    };

    this.activityDefinitionService.updateActivityDefinition(this.definition.id, data)
      .subscribe({
        next: value => {
          this.toasts.showSuccessToast(`Zaktualizowano definicję`)
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy aktualizacji definicji`)
        }
      })
  }

  delete() {
    Swal.fire({
      title: "Usuwanie definicji",
      text: "Czy napewno chcesz usunąć definicję?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Tak',
      cancelButtonText: 'Nie'
    }).then((result) => {
      if (result.value) {
        this.deleteDefinition()
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        this.toasts.showCancelledToast("Zrezygnowano z usuwania")
      }
    })
  }

  deleteDefinition(): void {
    this.activityDefinitionService.deleteActivityDefinition(this.definition.id)
      .subscribe({
        next: value => {
          this.toasts.showSuccessToast("Pomyślnie usunięto definicję")
          this.router.navigate(['/activityDefinitions']);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy usuwaniu definicji`)
        }
      })
  }

}
