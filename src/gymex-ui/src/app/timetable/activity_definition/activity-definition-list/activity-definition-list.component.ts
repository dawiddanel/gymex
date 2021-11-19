import {Component, OnInit} from '@angular/core';
import {ToastsService} from "../../../default/toasts.service";
import {ActivityDefinitionService} from "../activity-definition.service";
import {ActivityDefinition} from "../../../default/models/activity.model";

@Component({
  selector: 'app-activity-definition-list',
  templateUrl: './activity-definition-list.component.html',
  styleUrls: ['./activity-definition-list.component.css']
})
export class ActivityDefinitionListComponent implements OnInit {

  definitions?: ActivityDefinition[];

  constructor(private activityDefinitionService: ActivityDefinitionService,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
    this.retrieveDefinitions()
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

}
