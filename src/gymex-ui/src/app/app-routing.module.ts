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
import {AuthGuard} from "./security/auth.guard";
import {RegisterComponent} from "./security/register/register.component";
import {LoginComponent} from "./security/login/login.component";
import {MainComponent} from "./default/main/main.component";
import {EquipmentAddComponent} from "./assortment/equipment/equipment-add/equipment-add.component";
import {EquipmentEditComponent} from "./assortment/equipment/equipment-edit/equipment-edit.component";
import {EquipmentListComponent} from "./assortment/equipment/equipment-list/equipment-list.component";
import {ProfileComponent} from "./person/profile/profile.component";
import {TechnicalUserAddComponent} from "./person/technical-user-add/technical-user-add.component";
import {PersonListComponent} from "./person/person-list/person-list.component";
import {Role} from "./default/models/security.model";

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    canActivate: [AuthGuard],
    children: [
      {path: 'gyms', component: GymListComponent},
      {path: 'gyms/create', component: GymAddComponent},
      {path: 'gyms/:id', component: GymEditComponent},
      {path: 'assortment/equipmentDefinitions', component: EquipmentDefinitionListComponent},
      {path: 'assortment/equipmentDefinitions/create', component: EquipmentDefinitionAddComponent},
      {path: 'assortment/equipmentDefinitions/:id', component: EquipmentDefinitionEditComponent},
      {path: 'assortment/equipment/:gymId', component: EquipmentListComponent},
      {path: 'assortment/equipment/:gymId/create', component: EquipmentAddComponent},
      {path: 'assortment/equipment/:gymId/:id', component: EquipmentEditComponent},
      {path: 'timetable/activityDefinitions', component: ActivityDefinitionListComponent},
      {path: 'timetable/activityDefinitions/create', component: ActivityDefinitionAddComponent},
      {path: 'timetable/activityDefinitions/:id', component: ActivityDefinitionEditComponent},
      {path: 'profile', component: ProfileComponent},
      {path: 'technical/user/add', component: TechnicalUserAddComponent},
      {path: 'people/owners', component: PersonListComponent, data: {role: Role.OWNER}},
      {path: 'people/members', component: PersonListComponent, data: {role: Role.MEMBER}},
      {path: 'people/employees', component: PersonListComponent, data: {role: Role.EMPLOYEE}},
      {path: 'people/trainers', component: PersonListComponent, data: {role: Role.TRAINER}},
      {path: 'contact', component: ContactComponent}
    ]
  },

  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
