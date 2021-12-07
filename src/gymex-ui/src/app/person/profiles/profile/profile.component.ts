import {Component, Input, OnInit} from '@angular/core';
import {PersonService} from "../../person.service";
import {UserService} from "../../user.service";
import {emptyPerson, Person} from "../../../default/models/person.model";
import {emptyUser, User} from "../../../default/models/security.model";
import {ToastsService} from "../../../default/toasts.service";
import {SecurityUserService} from "../../../security/security-user.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  @Input() person: Person
  user: User = emptyUser();

  constructor(private personService: PersonService,
              private userService: UserService,
              readonly securityUserService: SecurityUserService,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
    this.user = this.securityUserService.getUser()
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
