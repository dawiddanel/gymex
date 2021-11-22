import { Component, OnInit } from '@angular/core';
import {SecurityService} from "../../security/security.service";
import {ToastsService} from "../toasts.service";
import {Router} from "@angular/router";
import {UserService} from "../../security/user.service";
import {User} from "../models/security.model";

@Component({
  selector: 'app-main-navbar',
  templateUrl: './main-navbar.component.html',
  styleUrls: ['./main-navbar.component.css']
})
export class MainNavbarComponent implements OnInit {

  constructor(private securityService: SecurityService,
              private userService: UserService,
              private toasts: ToastsService,
              private router: Router) { }

  ngOnInit(): void {
  }

  logout() {
    this.securityService.logout()
    this.toasts.showSuccessToast("Wylogowano")
    this.router.navigate(['/login']);
  }

}
