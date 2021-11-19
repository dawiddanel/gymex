import {Component, OnInit} from '@angular/core';
import {GymService} from "../gym.service";
import {ActivatedRoute, Router} from "@angular/router";
import {emptyGym, Gym} from "../../models/gym.model";
import {UpdateGym} from "../../models/gym.command.model";
import {ToastsService} from "../../default/toasts.service";
import Swal from 'sweetalert2/dist/sweetalert2.js';

@Component({
  selector: 'app-gym-edit',
  templateUrl: './gym-edit.component.html',
  styleUrls: ['./gym-edit.component.css']
})
export class GymEditComponent implements OnInit {

  gym: Gym = emptyGym()

  constructor(
    private gymService: GymService,
    private toasts: ToastsService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit(): void {
    this.getGym(this.route.snapshot.params['id']);
  }

  getGym(id: string): void {
    this.gymService.get(id)
      .subscribe({
        next: value => {
          this.gym = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu siłowni`)
        }
      })
  }

  updateGym(): void {
    const data: UpdateGym = {
      name: this.gym.name,
      capacity: this.gym.capacity,
      address: {
        country: this.gym.address.country,
        city: this.gym.address.city,
        postalCode: this.gym.address.postalCode,
        street: this.gym.address.street,
        buildingNumber: this.gym.address.buildingNumber
      }
    };

    this.gymService.update(this.gym.id, data)
      .subscribe({
        next: value => {
          this.toasts.showSuccessToast(`Zaktualizowano siłownię`)
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy aktualizacji siłowni`)
        }
      })
  }

  delete() {
    Swal.fire({
      title: "Usuwanie siłowni",
      text: "Czy napewno chcesz usunąć siłownię?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Tak',
      cancelButtonText: 'Nie'
    }).then((result) => {
      if (result.value) {
        this.deleteGym()
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        this.toasts.showCancelledToast("Zrezygnowano z usuwania")
      }
    })
  }

  deleteGym(): void {
    this.gymService.delete(this.gym.id)
      .subscribe({
        next: value => {
          this.toasts.showSuccessToast("Pomyślnie usunięto siłownię")
          this.router.navigate(['/gyms']);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy usuwaniu siłowni`)
        }
      })
  }

}
