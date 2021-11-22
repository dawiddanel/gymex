import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {
  CreateEquipmentDefinition,
  EquipmentDefinition,
  UpdateEquipmentDefinition
} from "../../default/models/equipment.model";
import {ApiService} from "../../default/api.service";

@Injectable({
  providedIn: 'root'
})
export class EquipmentDefinitionService {

  constructor(private api: ApiService) {
  }

  getAllEquipmentDefinition(): Observable<EquipmentDefinition[]> {
    return this.api.get('assortment/equipment/definition/all')
  }

  getEquipmentDefinition(id: any): Observable<EquipmentDefinition> {
    return this.api.get(`assortment/equipment/definition/${id}`)
  }

  createEquipmentDefinition(data: CreateEquipmentDefinition): Observable<EquipmentDefinition> {
    return this.api.post(`assortment/equipment/definition`, data)
  }

  updateEquipmentDefinition(id: any, data: UpdateEquipmentDefinition): Observable<EquipmentDefinition> {
    return this.api.put(`assortment/equipment/definition/${id}`, data)
  }

  deleteEquipmentDefinition(id: any): Observable<any> {
    return this.api.delete(`assortment/equipment/definition/${id}`)
  }
}
