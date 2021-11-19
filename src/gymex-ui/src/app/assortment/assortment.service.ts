import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {
  CreateEquipment,
  CreateEquipmentDefinition,
  EquipmentDefinition,
  UpdateEquipment,
  UpdateEquipmentDefinition
} from "../models/equipment.model";

const baseUrl = 'http://localhost:8085/gymex/assortment';

@Injectable({
  providedIn: 'root'
})
export class AssortmentService {

  constructor(private http: HttpClient) {
  }

  getAssortment(gymId: any): Observable<EquipmentDefinition> {
    return this.http.get<EquipmentDefinition>(`${baseUrl}/${gymId}`);
  }

  createEquipment(gymId: any, data: CreateEquipment): Observable<EquipmentDefinition> {
    return this.http.post<EquipmentDefinition>(`${baseUrl}/${gymId}/equipment`, data);
  }

  updateEquipment(gymId: any, id: any, data: UpdateEquipment): Observable<EquipmentDefinition> {
    return this.http.put<EquipmentDefinition>(`${baseUrl}/${gymId}/equipment/${id}`, data);
  }

  deleteEquipment(gymId: any, id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${gymId}/equipment/${id}`);
  }

  getAllEquipmentDefinition(): Observable<EquipmentDefinition[]> {
    return this.http.get<EquipmentDefinition[]>(`${baseUrl}/equipment/definition/all`);
  }

  getEquipmentDefinition(id: any): Observable<EquipmentDefinition> {
    return this.http.get<EquipmentDefinition>(`${baseUrl}/equipment/definition/${id}`);
  }

  createEquipmentDefinition(data: CreateEquipmentDefinition): Observable<EquipmentDefinition> {
    return this.http.post<EquipmentDefinition>(`${baseUrl}/equipment/definition`, data);
  }

  updateEquipmentDefinition(id: any, data: UpdateEquipmentDefinition): Observable<EquipmentDefinition> {
    return this.http.put<EquipmentDefinition>(`${baseUrl}/equipment/definition/${id}`, data);
  }

  deleteEquipmentDefinition(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/equipment/definition/${id}`);
  }
}
