import { Component, OnInit } from '@angular/core';
import {emptyMember, emptyOwner, Member, Owner} from "../../../default/models/person.model";
import {PersonService} from "../../person.service";
import {ToastsService} from "../../../default/toasts.service";

@Component({
  selector: 'app-owner-profile',
  templateUrl: './owner-profile.component.html',
  styleUrls: ['./owner-profile.component.css']
})
export class OwnerProfileComponent implements OnInit {

  owner: Owner = emptyOwner();

  constructor(private personService: PersonService,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
    this.getOwner();
  }

  getOwner(): void {
    this.personService.getCurrentOwner()
      .subscribe({
        next: value => {
          this.owner = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu danych osobowych`)
        }
      })
  }

}
