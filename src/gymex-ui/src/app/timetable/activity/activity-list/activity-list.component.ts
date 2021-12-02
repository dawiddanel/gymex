import {Component, Input, OnInit} from '@angular/core';
import {Activity} from "../../../default/models/activity.model";

@Component({
  selector: 'app-activity-list',
  templateUrl: './activity-list.component.html',
  styleUrls: ['./activity-list.component.css']
})
export class ActivityListComponent implements OnInit {

  @Input() activities: Activity[]
  @Input() gymId: number

  constructor() { }

  ngOnInit(): void {
  }

}
