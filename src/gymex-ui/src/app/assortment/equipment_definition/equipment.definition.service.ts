import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CreateEquipmentDefinition, EquipmentDefinition, UpdateEquipmentDefinition} from "../../default/models/equipment.model";

const baseUrl = 'http://localhost:8085/gymex/assortment/equipment/definition';

@Injectable({
  providedIn: 'root'
})
export class EquipmentDefinitionService {

  constructor(private http: HttpClient) {
  }

  getAllEquipmentDefinition(): Observable<EquipmentDefinition[]> {
    return this.http.get<EquipmentDefinition[]>(`${baseUrl}/all`);
  }

  getEquipmentDefinition(id: any): Observable<EquipmentDefinition> {
    return this.http.get<EquipmentDefinition>(`${baseUrl}/${id}`);
  }

  createEquipmentDefinition(data: CreateEquipmentDefinition): Observable<EquipmentDefinition> {
    return this.http.post<EquipmentDefinition>(`${baseUrl}`, data);
  }

  updateEquipmentDefinition(id: any, data: UpdateEquipmentDefinition): Observable<EquipmentDefinition> {
    return this.http.put<EquipmentDefinition>(`${baseUrl}/${id}`, data);
  }

  deleteEquipmentDefinition(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
}
