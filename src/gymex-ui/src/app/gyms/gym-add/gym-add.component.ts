import {Component, OnInit} from '@angular/core';
import {GymService} from "../gym.service";
import {CreateGym, emptyCreateGym} from "../../models/gym.command.model";
import {ToastsService} from "../../default/toasts.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-gym-add',
  templateUrl: './gym-add.component.html',
  styleUrls: ['./gym-add.component.css']
})
export class GymAddComponent implements OnInit {

  gymCommand: CreateGym = emptyCreateGym()

  constructor(private gymService: GymService,
              private toasts: ToastsService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  saveGym(): void {
    const data: CreateGym = {
      name: this.gymCommand.name,
      capacity: this.gymCommand.capacity,
      timetableStartDate: this.gymCommand.timetableStartDate,
      address: this.gymCommand.address
    };

    this.gymService.create(data)
      .subscribe({
        next: response => {
          this.toasts.showSuccessToast(`Siłownia poprawnie dodana`)
          this.router.navigate(['/gyms']);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu listy`)
        }
      })
  }

}
