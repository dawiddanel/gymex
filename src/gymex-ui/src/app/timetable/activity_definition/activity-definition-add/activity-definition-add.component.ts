import {Component, OnInit} from '@angular/core';
import {ToastsService} from "../../../default/toasts.service";
import {Router} from "@angular/router";
import {ActivityDefinitionService} from "../activity-definition.service";
import {enumSelector} from "../../../default/utilities";
import {CreateActivityDefinition, emptyCreateActivityDefinition} from "../../../default/models/activity.command.model";
import {Level} from "../../../default/models/activity.model";

@Component({
  selector: 'app-activity-definition-add',
  templateUrl: './activity-definition-add.component.html',
  styleUrls: ['./activity-definition-add.component.css']
})
export class ActivityDefinitionAddComponent implements OnInit {

  definitionCommand: CreateActivityDefinition = emptyCreateActivityDefinition()
  levels = enumSelector(Level)

  constructor(private activityDefinitionService: ActivityDefinitionService,
              private toasts: ToastsService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  saveDefinition(): void {
    const data: CreateActivityDefinition = {
      name: this.definitionCommand.name,
      description: this.definitionCommand.description,
      level: this.definitionCommand.level
    };

    this.activityDefinitionService.createActivityDefinition(data)
      .subscribe({
        next: response => {
          this.toasts.showSuccessToast(`Definicja poprawnie dodana`)
          this.router.navigate(['/activityDefinitions']);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy dodawaniu definicji`)
        }
      })
  }

}
