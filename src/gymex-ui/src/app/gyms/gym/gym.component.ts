import {Component, Input, OnInit} from '@angular/core';
import {Gym} from "../../default/models/gym.model";

@Component({
  selector: 'app-gym',
  templateUrl: './gym.component.html',
  styleUrls: ['./gym.component.css']
})
export class GymComponent implements OnInit {

  @Input() gym: Gym;

  constructor() {
  }

  ngOnInit(): void {
  }

}
