import {Component, OnInit} from '@angular/core';
import {
  BodyPart,
  emptyEquipmentDefinition,
  EquipmentDefinition,
  EquipmentType,
  Purpose,
  UpdateEquipmentDefinition
} from "../../../default/models/equipment.model";
import {ToastsService} from "../../../default/toasts.service";
import {ActivatedRoute, Router} from "@angular/router";
import Swal from 'sweetalert2/dist/sweetalert2.js';
import {enumSelector} from "../../../default/utilities";
import {EquipmentDefinitionService} from "../equipment.definition.service";

@Component({
  selector: 'app-equipment-definition-edit',
  templateUrl: './equipment-definition-edit.component.html',
  styleUrls: ['./equipment-definition-edit.component.css']
})
export class EquipmentDefinitionEditComponent implements OnInit {

  definition: EquipmentDefinition = emptyEquipmentDefinition()
  equipmentTypes = enumSelector(EquipmentType)
  purposes = enumSelector(Purpose)
  bodyParts = enumSelector(BodyPart)

  constructor(private equipmentDefinitionService: EquipmentDefinitionService,
              private toasts: ToastsService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getDefinition(this.route.snapshot.params['id']);
  }

  getDefinition(id: string): void {
    this.equipmentDefinitionService.getEquipmentDefinition(id)
      .subscribe({
        next: value => {
          this.definition = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu definicji`)
        }
      })
  }

  updateDefinition(): void {
    const data: UpdateEquipmentDefinition = {
      name: this.definition.name,
      description: this.definition.description,
      purpose: this.definition.purpose,
      type: this.definition.type,
      weight: this.definition.weight,
      aimedBodyParts: this.definition.aimedBodyParts,
    };

    this.equipmentDefinitionService.updateEquipmentDefinition(this.definition.id, data)
      .subscribe({
        next: value => {
          this.toasts.showSuccessToast(`Zaktualizowano definicję`)
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy aktualizacji definicji`)
        }
      })
  }

  delete() {
    Swal.fire({
      title: "Usuwanie definicji",
      text: "Czy napewno chcesz usunąć definicję?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Tak',
      cancelButtonText: 'Nie'
    }).then((result) => {
      if (result.value) {
        this.deleteDefinition()
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        this.toasts.showCancelledToast("Zrezygnowano z usuwania")
      }
    })
  }

  deleteDefinition(): void {
    this.equipmentDefinitionService.deleteEquipmentDefinition(this.definition.id)
      .subscribe({
        next: value => {
          this.toasts.showSuccessToast("Pomyślnie usunięto definicję")
          this.router.navigate(['/assortment/equipmentDefinitions']);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy usuwaniu definicji`)
        }
      })
  }

}
