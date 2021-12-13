import {Component, Input, OnInit} from '@angular/core';
import {Gym} from "../../default/models/gym.model";
import {SecurityUserService} from "../../security/security-user.service";
import {GymService} from "../gym.service";
import {ToastsService} from "../../default/toasts.service";

@Component({
  selector: 'app-gym',
  templateUrl: './gym.component.html',
  styleUrls: ['./gym.component.css']
})
export class GymComponent implements OnInit {

  @Input() gym: Gym;

  constructor(private gymService: GymService,
              readonly userService: SecurityUserService,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
  }

  countActualPresence(): void {
    this.gymService.allGymCurrentPresences(this.gym.id)
      .subscribe({
        next: value => {
          const peopleNumber = value ? value.length : 0;
          this.toasts.showInfoToast("Aktualna liczba ćwiczących to: " + value.length + ", wolne miejsca: " + (this.gym.capacity - peopleNumber))
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu listy osób`)
        }
      })
  }

}
