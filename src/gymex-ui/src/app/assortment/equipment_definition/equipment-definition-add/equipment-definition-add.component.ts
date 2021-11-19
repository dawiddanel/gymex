import {Component, OnInit} from '@angular/core';
import {
  BodyPart,
  CreateEquipmentDefinition,
  emptyCreateEquipmentDefinition,
  EquipmentType, Purpose
} from "../../../models/equipment.model";
import {ToastsService} from "../../../default/toasts.service";
import {Router} from "@angular/router";
import {AssortmentService} from "../../assortment.service";
import {enumSelector} from "../../../default/utilities";

@Component({
  selector: 'app-equipment-definition-add',
  templateUrl: './equipment-definition-add.component.html',
  styleUrls: ['./equipment-definition-add.component.css']
})
export class EquipmentDefinitionAddComponent implements OnInit {

  definitionCommand: CreateEquipmentDefinition = emptyCreateEquipmentDefinition()
  equipmentTypes = enumSelector(EquipmentType)
  purposes = enumSelector(Purpose)
  bodyParts = enumSelector(BodyPart)

  constructor(private assortmentService: AssortmentService,
              private toasts: ToastsService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  saveDefinition(): void {
    const data: CreateEquipmentDefinition = {
      name: this.definitionCommand.name,
      description: this.definitionCommand.description,
      purpose: this.definitionCommand.purpose,
      type: this.definitionCommand.type,
      weight: this.definitionCommand.weight,
      aimedBodyParts: this.definitionCommand.aimedBodyParts,
    };

    this.assortmentService.createEquipmentDefinition(data)
      .subscribe({
        next: response => {
          this.toasts.showSuccessToast(`Definicja poprawnie dodana`)
          this.router.navigate(['/equipmentDefinitions']);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu listy`)
        }
      })
  }

}
