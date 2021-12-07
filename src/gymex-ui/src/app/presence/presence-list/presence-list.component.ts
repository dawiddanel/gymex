import { Component, OnInit } from '@angular/core';
import {Presence} from "../../default/models/gym.model";
import {GymService} from "../../gyms/gym.service";
import {ActivatedRoute} from "@angular/router";
import {ToastsService} from "../../default/toasts.service";
import {SecurityUserService} from "../../security/security-user.service";

@Component({
  selector: 'app-presence-list',
  templateUrl: './presence-list.component.html',
  styleUrls: ['./presence-list.component.css']
})
export class PresenceListComponent implements OnInit {

  presences: Presence[]
  gymId: number

  constructor(private gymService: GymService,
              readonly userService: SecurityUserService,
              private route: ActivatedRoute,
              private toasts: ToastsService) { }

  ngOnInit(): void {
    this.gymId = this.route.snapshot.params['gymId']
    this.retrievePresences()
  }

  retrievePresences(): void {
    this.gymService.allGymCurrentPresences(this.gymId)
      .subscribe({
        next: value => {
          this.presences = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu listy osób`)
        }
      })
  }

}
