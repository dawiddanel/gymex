import {Component, Input, OnInit} from '@angular/core';
import {Presence} from "../../default/models/gym.model";
import {GymService} from "../../gyms/gym.service";
import {ToastsService} from "../../default/toasts.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-presence',
  templateUrl: './presence.component.html',
  styleUrls: ['./presence.component.css']
})
export class PresenceComponent implements OnInit {

  @Input() presence: Presence
  @Input() gymId: number

  constructor(private gymService: GymService,
              private toasts: ToastsService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  finishPresence(): void {
    this.gymService.finishPresence(this.gymId, this.presence.id)
      .subscribe({
        next: response => {
          this.toasts.showSuccessToast(`Poprawnie zakończono obecność`)
          this.presence.endTime = new Date()
          this.router.navigate([`/presence/${this.gymId}`]);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy kończeniu obecności`)
        }
      })
  }

}
