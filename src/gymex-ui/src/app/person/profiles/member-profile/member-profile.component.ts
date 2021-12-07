import {Component, OnInit} from '@angular/core';
import {PersonService} from "../../person.service";
import {ToastsService} from "../../../default/toasts.service";
import {emptyMember, Member} from "../../../default/models/person.model";

@Component({
  selector: 'app-member-profile',
  templateUrl: './member-profile.component.html',
  styleUrls: ['./member-profile.component.css']
})
export class MemberProfileComponent implements OnInit {

  member: Member = emptyMember();

  constructor(private personService: PersonService,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
    this.getMember();
  }

  getMember(): void {
    this.personService.getCurrentMember()
      .subscribe({
        next: value => {
          this.member = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu danych osobowych`)
        }
      })
  }

}
