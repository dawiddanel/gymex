import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GymListComponent} from "./gyms/gym-list/gym-list.component";
import {GymAddComponent} from "./gyms/gym-add/gym-add.component";
import {GymEditComponent} from "./gyms/gym-edit/gym-edit.component";
import {ContactComponent} from "./default/contact/contact.component";
import {EquipmentDefinitionListComponent} from "./assortment/equipment_definition/equipment-definition-list/equipment-definition-list.component";
import {EquipmentDefinitionAddComponent} from "./assortment/equipment_definition/equipment-definition-add/equipment-definition-add.component";
import {EquipmentDefinitionEditComponent} from "./assortment/equipment_definition/equipment-definition-edit/equipment-definition-edit.component";

const routes: Routes = [
  {path: '', redirectTo: 'gyms', pathMatch: 'full'},
  {path: 'gyms', component: GymListComponent},
  {path: 'gyms/create', component: GymAddComponent},
  {path: 'gyms/:id', component: GymEditComponent},
  {path: 'equipmentDefinitions', component: EquipmentDefinitionListComponent},
  {path: 'equipmentDefinitions/create', component: EquipmentDefinitionAddComponent},
  {path: 'equipmentDefinitions/:id', component: EquipmentDefinitionEditComponent},
  {path: 'contact', component: ContactComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
