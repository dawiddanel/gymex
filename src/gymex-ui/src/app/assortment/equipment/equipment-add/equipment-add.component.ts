import { Component, OnInit } from '@angular/core';
import {EquipmentDefinitionService} from "../../equipment_definition/equipment.definition.service";
import {ToastsService} from "../../../default/toasts.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AssortmentService} from "../../assortment.service";
import {
  CreateEquipment,
  CreateEquipmentDefinition,
  emptyCreateEquipment,
  EquipmentDefinition
} from "../../../default/models/equipment.model";

@Component({
  selector: 'app-equipment-add',
  templateUrl: './equipment-add.component.html',
  styleUrls: ['./equipment-add.component.css']
})
export class EquipmentAddComponent implements OnInit {

  gymId: number

  definitions?: EquipmentDefinition[];
  equipmentCommand: CreateEquipment = emptyCreateEquipment()

  constructor(private assortmentService: AssortmentService,
              private equipmentDefinitionService: EquipmentDefinitionService,
              private toasts: ToastsService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.gymId = this.route.snapshot.params['gymId']
    this.retrieveDefinitions()
  }

  retrieveDefinitions(): void {
    this.equipmentDefinitionService.getAllEquipmentDefinition()
      .subscribe({
        next: value => {
          console.log("pobralem wartosci")
          this.definitions = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu listy definicji sprzętu`)
        }
      })
  }

  saveEquipment(): void {
    this.assortmentService.createEquipment(this.gymId, this.equipmentCommand)
      .subscribe({
        next: response => {
          this.toasts.showSuccessToast(`Poprawnie dodano sprzęt`)
          this.router.navigate([`/assortment/equipment/${this.gymId}`]);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy dodawaniu wyposażenia`)
        }
      })
  }

}
