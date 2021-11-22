import {Component, OnInit} from '@angular/core';
import {emptyRegister, Register} from "../../default/models/security.model";
import {SecurityService} from "../security.service";
import {ToastsService} from "../../default/toasts.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerCommand: Register = emptyRegister()

  constructor(private securityService: SecurityService,
              private toasts: ToastsService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  register(): void {
    this.securityService.register(this.registerCommand)
      .subscribe({
        next: response => {
          this.toasts.showSuccessToast(`Udana rejestracja, możesz się zalogować`)
          this.router.navigate(['/login']);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy rejestracji`)
        }
      })
  }

}
