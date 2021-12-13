import {Component, Input, OnInit} from '@angular/core';
import {Pass} from "../../../default/models/person.model";

@Component({
  selector: 'app-pass',
  templateUrl: './pass.component.html',
  styleUrls: ['./pass.component.css']
})
export class PassComponent implements OnInit {

  @Input() pass: Pass

  constructor() { }

  ngOnInit(): void {
  }

}
