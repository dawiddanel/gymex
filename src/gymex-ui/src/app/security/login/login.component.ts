import {Component, OnInit} from '@angular/core';
import {SecurityService} from "../security.service";
import {ToastsService} from "../../default/toasts.service";
import {Router} from "@angular/router";
import {emptySignIn, SignIn} from "../../default/models/security.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  signIn: SignIn = emptySignIn()

  constructor(private securityService: SecurityService,
              private toasts: ToastsService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  login(): void {
    this.securityService.login(this.signIn)
      .subscribe({
        next: response => {
          this.securityService.keepToken(response.token)
          this.securityService.getUser()
            .subscribe({
              next: response => {
                this.securityService.keepUser(response)
              },
              error: err => {
                this.toasts.showErrorToast(`Błąd przy pobieraniu szczegółow konta`)
              }
            })
          this.toasts.showSuccessToast(`Zalogowano`)
          this.router.navigate(['/gyms']);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy logowaniu`)
        }
      })
  }

}
