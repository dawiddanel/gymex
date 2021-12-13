import {Component, OnInit} from '@angular/core';
import {ToastsService} from "../../default/toasts.service";
import {Router} from "@angular/router";
import {CreateTechnicalUser, emptyCreateTechnicalUser} from "../../default/models/person.command.model";
import {UserService} from "../user.service";
import {Role} from "../../default/models/security.model";
import {enumSelector} from "../../default/utilities";

@Component({
  selector: 'app-technical-user-add',
  templateUrl: './technical-user-add.component.html',
  styleUrls: ['./technical-user-add.component.css']
})
export class TechnicalUserAddComponent implements OnInit {

  userCommand: CreateTechnicalUser = emptyCreateTechnicalUser()
  roles = enumSelector(Role)

  constructor(private userService: UserService,
              private toasts: ToastsService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  saveTechnicalUser(): void {
    this.userService.createTechnicalUser(this.userCommand)
      .subscribe({
        next: response => {
          this.toasts.showSuccessToast(`Użytkownik dodany poprawnie`)
          this.router.navigate(['/assortment/equipmentDefinitions']);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy dodawaniu użytkownika`)
        }
      })
  }

}
