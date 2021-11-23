import {Component, OnInit} from '@angular/core';
import {AssortmentService} from "../../assortment.service";
import {ToastsService} from "../../../default/toasts.service";
import {ActivatedRoute} from "@angular/router";
import {Assortment} from "../../../default/models/assortment.model";

@Component({
  selector: 'app-equipment-list',
  templateUrl: './equipment-list.component.html',
  styleUrls: ['./equipment-list.component.css']
})
export class EquipmentListComponent implements OnInit {

  assortment?: Assortment
  gymId: number

  constructor(private assortmentService: AssortmentService,
              private toasts: ToastsService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.gymId = this.route.snapshot.params['gymId']
    this.retrieveAssortment()

  }

  retrieveAssortment(): void {
    this.assortmentService.getAssortment(this.gymId)
      .subscribe({
        next: value => {
          this.assortment = value
        },
        error: err => {
          this.toasts.showErrorToast(`Błąd przy pobieraniu inwentarza`)
        }
      })
  }

}
