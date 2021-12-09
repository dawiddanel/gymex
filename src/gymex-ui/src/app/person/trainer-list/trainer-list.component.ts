import { Component, OnInit } from '@angular/core';
import {Person, Trainer} from "../../default/models/person.model";
import {PersonService} from "../person.service";
import {ActivatedRoute} from "@angular/router";
import {ToastsService} from "../../default/toasts.service";

@Component({
  selector: 'app-trainer-list',
  templateUrl: './trainer-list.component.html',
  styleUrls: ['./trainer-list.component.css']
})
export class TrainerListComponent implements OnInit {

  people: Trainer[]

  constructor(private personService: PersonService,
              private route: ActivatedRoute,
              private toasts: ToastsService) { }

  ngOnInit(): void {
    this.retrieveTrainers()
  }

  retrieveTrainers(): void {
    this.personService.getAllTrainer()
      .subscribe({
        next: value => {
          this.people = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu trenerów`)
        }
      })
  }

}
