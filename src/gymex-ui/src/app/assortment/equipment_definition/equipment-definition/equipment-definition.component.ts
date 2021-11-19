import {Component, Input, OnInit} from '@angular/core';
import {EquipmentDefinition} from "../../../default/models/equipment.model";

@Component({
  selector: 'app-equipment-definition',
  templateUrl: './equipment-definition.component.html',
  styleUrls: ['./equipment-definition.component.css']
})
export class EquipmentDefinitionComponent implements OnInit {

  @Input() definition: EquipmentDefinition;

  constructor() { }

  ngOnInit(): void {
  }

}
