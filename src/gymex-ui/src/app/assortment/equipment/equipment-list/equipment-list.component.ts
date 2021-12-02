import {Component, Input, OnInit} from '@angular/core';
import {Equipment} from "../../../default/models/equipment.model";

@Component({
  selector: 'app-equipment-list',
  templateUrl: './equipment-list.component.html',
  styleUrls: ['./equipment-list.component.css']
})
export class EquipmentListComponent implements OnInit {

  @Input() equipmentList: Equipment[]
  @Input() gymId: number

  constructor() { }

  ngOnInit(): void {
  }

}
