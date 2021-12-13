import {Component, OnInit} from '@angular/core';
import {PersonService} from "../../person/person.service";
import {ToastsService} from "../../default/toasts.service";
import {ActivatedRoute, Router} from "@angular/router";
import {GymService} from "../../gyms/gym.service";
import {CreatePresence, emptyCreatePresence} from "../../default/models/gym.command.model";
import {Member} from "../../default/models/person.model";

@Component({
  selector: 'app-presence-add',
  templateUrl: './presence-add.component.html',
  styleUrls: ['./presence-add.component.css']
})
export class PresenceAddComponent implements OnInit {

  gymId: number

  members?: Member[]
  presenceCommand: CreatePresence = emptyCreatePresence()

  constructor(private gymService: GymService,
              private personService: PersonService,
              private toasts: ToastsService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.gymId = this.route.snapshot.params['gymId']
    this.retrieveMembers()
  }

  retrieveMembers(): void {
    this.personService.getAllMembers()
      .subscribe({
        next: (value: Member[]) => {
          this.members = value.filter((member => !!member.pass));
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu listy osób`)
        }
      })
  }

  savePresence(): void {
    this.gymService.createPresence(this.gymId, this.presenceCommand)
      .subscribe({
        next: response => {
          this.toasts.showSuccessToast(`Poprawnie dodano obecność`)
          this.router.navigate([`/presence/${this.gymId}`]);
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy dodawaniu obecności`)
        }
      })
  }
}
