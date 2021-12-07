import {Component, Input, OnInit} from '@angular/core';
import {Equipment} from "../../../default/models/equipment.model";
import {SecurityUserService} from "../../../security/security-user.service";

@Component({
  selector: 'app-equipment',
  templateUrl: './equipment.component.html',
  styleUrls: ['./equipment.component.css']
})
export class EquipmentComponent implements OnInit {

  @Input() equipment: Equipment
  @Input() gymId: number

  constructor(readonly userService: SecurityUserService) {
  }

  ngOnInit(): void {
  }

}
