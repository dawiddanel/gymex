import {Component, OnInit} from '@angular/core';
import {emptyGym, Gym} from "../../default/models/gym.model";
import {GymService} from "../gym.service";
import {ToastsService} from "../../default/toasts.service";
import {SecurityUserService} from "../../security/security-user.service";

@Component({
  selector: 'app-gym-list',
  templateUrl: './gym-list.component.html',
  styleUrls: ['./gym-list.component.css']
})
export class GymListComponent implements OnInit {

  gyms?: Gym[];

  constructor(
    private gymService: GymService,
    readonly userService: SecurityUserService,
    private toasts: ToastsService
  ) {
  }

  ngOnInit(): void {
    this.retrieveGyms();
  }

  retrieveGyms(): void {
    this.gymService.getAll()
      .subscribe({
        next: value => {
          this.gyms = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu listy`)
        }
      })
  }
}
