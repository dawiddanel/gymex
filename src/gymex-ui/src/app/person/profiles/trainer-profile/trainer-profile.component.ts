import { Component, OnInit } from '@angular/core';
import {emptyMember, emptyTrainer, Member, Trainer} from "../../../default/models/person.model";
import {PersonService} from "../../person.service";
import {ToastsService} from "../../../default/toasts.service";

@Component({
  selector: 'app-trainer-profile',
  templateUrl: './trainer-profile.component.html',
  styleUrls: ['./trainer-profile.component.css']
})
export class TrainerProfileComponent implements OnInit {

  trainer: Trainer = emptyTrainer();

  constructor(private personService: PersonService,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
    this.getTrainer();
  }

  getTrainer(): void {
    this.personService.getCurrentTrainer()
      .subscribe({
        next: value => {
          this.trainer = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu danych osobowych`)
        }
      })
  }

}
