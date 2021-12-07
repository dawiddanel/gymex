import {Component, OnInit} from '@angular/core';
import {Person} from "../../default/models/person.model";
import {Role} from "../../default/models/security.model";
import {PersonService} from "../person.service";
import {ToastsService} from "../../default/toasts.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit {

  people: Person[]
  role: string
  roleValues = Role

  constructor(private personService: PersonService,
              private route: ActivatedRoute,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
    this.role = this.route.snapshot.data['role']
    switch (this.role) {
      case Role.OWNER:
        this.retrieveOwners()
        break;
      case Role.EMPLOYEE:
        this.retrieveEmployee()
        break;
      case Role.TRAINER:
        this.retrieveTrainers()
        break;
      case Role.MEMBER:
        this.retrieveMembers()
        break;
    }

  }

  retrieveMembers(): void {
    this.personService.getAllMembers()
      .subscribe({
        next: value => {
          this.people = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu klientów`)
        }
      })
  }

  retrieveOwners(): void {
    this.personService.getAllOwners()
      .subscribe({
        next: value => {
          this.people = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu właścicieli`)
        }
      })
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

  retrieveEmployee(): void {
    this.personService.getAllEmployee()
      .subscribe({
        next: value => {
          this.people = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu pracowników`)
        }
      })
  }

}
