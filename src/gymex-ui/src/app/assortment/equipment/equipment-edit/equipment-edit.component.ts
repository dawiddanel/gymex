import {Component, OnInit} from '@angular/core';
import {AssortmentService} from "../../assortment.service";
import {ToastsService} from "../../../default/toasts.service";
import {ActivatedRoute, Router} from "@angular/router";
import {emptyEquipment, Equipment, UpdateEquipment} from "../../../default/models/equipment.model";
import Swal from 'sweetalert2/dist/sweetalert2.js';

@Component({
  selector: 'app-equipment-edit',
  templateUrl: './equipment-edit.component.html',
  styleUrls: ['./equipment-edit.component.css']
})
export class EquipmentEditComponent implements OnInit {

  gymId: number
  equipment: Equipment = emptyEquipment()

  constructor(private assortmentService: AssortmentService,
              private toasts: ToastsService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.gymId = this.route.snapshot.params['gymId']
    this.getEquipment(this.route.snapshot.params['id']);
  }

  getEquipment(id: string): void {
    this.assortmentService.getEquipment(this.gymId, id)
      .subscribe({
        next: value => {
          this.equipment = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu wyposażenia`)
        }
      })
  }

  updateEquipment(): void {
    const data: UpdateEquipment = {
      quantity: this.equipment.quantity,
    };

    this.assortmentService.updateEquipment(this.gymId, this.equipment.id, data)
      .subscribe({
        next: value => {
          this.toasts.showSuccessToast(`Zaktualizowano wyposażenie`)
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy aktualizacji wyposażenia`)
        }
      })
  }

  delete() {
    Swal.fire({
      title: "Usuwanie wyposażenia",
      text: "Czy napewno chcesz usunąć wyposażenie?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Tak',
      cancelButtonText: 'Nie'
    }).then((result) => {
      if (result.value) {
        this.deleteEquipment()
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        this.toasts.showCancelledToast("Zrezygnowano z usuwania")
      }
    })
  }

  deleteEquipment(): void {
    this.assortmentService.deleteEquipment(this.gymId, this.equipment.id)
      .subscribe({
        next: value => {
          this.toasts.showSuccessToast("Pomyślnie usunięto wyposażenie")
          this.router.navigate([`/assortment/equipment/${this.gymId}`]);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy usuwaniu definicji`)
        }
      })
  }


}
