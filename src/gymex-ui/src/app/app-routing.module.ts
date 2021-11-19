import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GymListComponent} from "./gyms/gym-list/gym-list.component";
import {GymAddComponent} from "./gyms/gym-add/gym-add.component";
import {GymEditComponent} from "./gyms/gym-edit/gym-edit.component";
import {ContactComponent} from "./default/contact/contact.component";
import {EquipmentDefinitionListComponent} from "./assortment/equipment_definition/equipment-definition-list/equipment-definition-list.component";
import {EquipmentDefinitionAddComponent} from "./assortment/equipment_definition/equipment-definition-add/equipment-definition-add.component";
import {EquipmentDefinitionEditComponent} from "./assortment/equipment_definition/equipment-definition-edit/equipment-definition-edit.component";
import {ActivityDefinitionListComponent} from "./timetable/activity_definition/activity-definition-list/activity-definition-list.component";
import {ActivityDefinitionAddComponent} from "./timetable/activity_definition/activity-definition-add/activity-definition-add.component";
import {ActivityDefinitionEditComponent} from "./timetable/activity_definition/activity-definition-edit/activity-definition-edit.component";

const routes: Routes = [
  {path: '', redirectTo: 'gyms', pathMatch: 'full'},
  {path: 'gyms', component: GymListComponent},
  {path: 'gyms/create', component: GymAddComponent},
  {path: 'gyms/:id', component: GymEditComponent},
  {path: 'equipmentDefinitions', component: EquipmentDefinitionListComponent},
  {path: 'equipmentDefinitions/create', component: EquipmentDefinitionAddComponent},
  {path: 'equipmentDefinitions/:id', component: EquipmentDefinitionEditComponent},
  {path: 'activityDefinitions', component: ActivityDefinitionListComponent},
  {path: 'activityDefinitions/create', component: ActivityDefinitionAddComponent},
  {path: 'activityDefinitions/:id', component: ActivityDefinitionEditComponent},
  {path: 'contact', component: ContactComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
