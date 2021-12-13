import { Component, OnInit } from '@angular/core';
import {Presence} from "../../default/models/gym.model";
import {GymService} from "../../gyms/gym.service";
import {SecurityUserService} from "../../security/security-user.service";
import {ActivatedRoute} from "@angular/router";
import {ToastsService} from "../../default/toasts.service";

@Component({
  selector: 'app-presences',
  templateUrl: './presences.component.html',
  styleUrls: ['./presences.component.css']
})
export class PresencesComponent implements OnInit {

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
