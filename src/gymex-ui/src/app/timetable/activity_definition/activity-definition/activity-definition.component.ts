import {Component, Input, OnInit} from '@angular/core';
import {ActivityDefinition} from "../../../models/activity.model";

@Component({
  selector: 'app-activity-definition',
  templateUrl: './activity-definition.component.html',
  styleUrls: ['./activity-definition.component.css']
})
export class ActivityDefinitionComponent implements OnInit {

  @Input() definition: ActivityDefinition

  constructor() { }

  ngOnInit(): void {
  }

}
