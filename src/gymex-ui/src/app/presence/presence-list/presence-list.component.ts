import {Component, Input, OnInit} from '@angular/core';
import {Presence} from "../../default/models/gym.model";
import {SecurityUserService} from "../../security/security-user.service";

@Component({
  selector: 'app-presence-list',
  templateUrl: './presence-list.component.html',
  styleUrls: ['./presence-list.component.css']
})
export class PresenceListComponent implements OnInit {

  @Input() presences: Presence[]
  @Input() gymId: number

  constructor(readonly userService: SecurityUserService) {
  }

  ngOnInit(): void {
  }

}
