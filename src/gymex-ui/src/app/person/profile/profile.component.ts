import {Component, OnInit} from '@angular/core';
import {PersonService} from "../person.service";
import {UserService} from "../user.service";
import {emptyPerson, Person} from "../../default/models/person.model";
import {emptyUser, User} from "../../default/models/security.model";
import {ToastsService} from "../../default/toasts.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  person: Person = emptyPerson()
  user: User = emptyUser()

  constructor(private personService: PersonService,
              private userService: UserService,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
    this.getPerson()
    this.getUser()
  }

  getPerson(): void {
    this.personService.getCurrentPerson()
      .subscribe({
        next: value => {
          this.person = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu danych osobowych`)
        }
      })
  }

  getUser(): void {
    this.userService.getCurrentUser()
      .subscribe({
        next: value => {
          this.user = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu danych konta`)
        }
      })
  }

}
