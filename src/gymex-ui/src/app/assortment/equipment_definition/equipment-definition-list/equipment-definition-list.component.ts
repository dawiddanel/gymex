import {Component, OnInit} from '@angular/core';
import {EquipmentDefinition} from "../../../models/equipment.model";
import {ToastsService} from "../../../default/toasts.service";
import {EquipmentDefinitionService} from "../equipment.definition.service";

@Component({
  selector: 'app-equipment-definition-list',
  templateUrl: './equipment-definition-list.component.html',
  styleUrls: ['./equipment-definition-list.component.css']
})
export class EquipmentDefinitionListComponent implements OnInit {

  definitions?: EquipmentDefinition[];

  constructor(
    private equipmentDefinitionService: EquipmentDefinitionService,
    private toasts: ToastsService
  ) {
  }

  ngOnInit(): void {
    this.retrieveDefinitions()
  }

  retrieveDefinitions(): void {
    this.equipmentDefinitionService.getAllEquipmentDefinition()
      .subscribe({
        next: value => {
          this.definitions = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu listy definicji sprzętu`)
        }
      })
  }

}
