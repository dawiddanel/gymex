import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {CreateEquipment, Equipment, UpdateEquipment} from "../default/models/equipment.model";
import {Assortment} from "../default/models/assortment.model";
import {ApiService} from "../default/api.service";

@Injectable({
  providedIn: 'root'
})
export class AssortmentService {

  constructor(private api: ApiService) {
  }

  getAssortment(gymId: any): Observable<Assortment> {
    return this.api.get(`assortment/${gymId}`)
  }

  getEquipment(gymId: any, equipmentId: any): Observable<Equipment> {
    return this.api.get(`assortment/${gymId}/equipment/${equipmentId}`)
  }

  createEquipment(gymId: any, data: CreateEquipment): Observable<Equipment> {
    return this.api.post(`assortment/${gymId}/equipment`, data)
  }

  updateEquipment(gymId: any, id: any, data: UpdateEquipment): Observable<Equipment> {
    return this.api.put(`assortment/${gymId}/equipment/${id}`, data)
  }

  deleteEquipment(gymId: any, id: any): Observable<any> {
    return this.api.delete(`assortment/${gymId}/equipment/${id}`)
  }
}
