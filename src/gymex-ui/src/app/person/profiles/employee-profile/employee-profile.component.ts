import {Component, OnInit} from '@angular/core';
import {Employee, emptyEmployee} from "../../../default/models/person.model";
import {PersonService} from "../../person.service";
import {ToastsService} from "../../../default/toasts.service";

@Component({
  selector: 'app-employee-profile',
  templateUrl: './employee-profile.component.html',
  styleUrls: ['./employee-profile.component.css']
})
export class EmployeeProfileComponent implements OnInit {

  employee: Employee = emptyEmployee();

  constructor(private personService: PersonService,
              private toasts: ToastsService) {
  }

  ngOnInit(): void {
    this.getEmployee();
  }

  getEmployee(): void {
    this.personService.getCurrentEmployee()
      .subscribe({
        next: value => {
          this.employee = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu danych osobowych`)
        }
      })
  }

}
