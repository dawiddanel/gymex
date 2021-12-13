import { Component, OnInit } from '@angular/core';
import {CreatePass, emptyCreatePass} from "../../../default/models/person.command.model";
import {PersonService} from "../../person.service";
import {ToastsService} from "../../../default/toasts.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-pass-add',
  templateUrl: './pass-add.component.html',
  styleUrls: ['./pass-add.component.css']
})
export class PassAddComponent implements OnInit {

  passCommand: CreatePass = emptyCreatePass()

  constructor(private personService: PersonService,
              private toasts: ToastsService,
              private router: Router) { }

  ngOnInit(): void {
  }

  buyPass(): void {
    this.personService.buyPass(this.passCommand)
      .subscribe({
        next: response => {
          this.toasts.showSuccessToast(`Zakup karnetu udany`)
          this.router.navigate(['/profile/member']);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy kupnie karnetu`)
        }
      })
  }

}
