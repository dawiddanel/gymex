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
import {AsssortmentComponent} from "./assortment/assortment/asssortment.component";
import {TechnicalUserAddComponent} from "./person/technical-user-add/technical-user-add.component";
import {PersonListComponent} from "./person/person-list/person-list.component";
import {Role} from "./default/models/security.model";
import {TimetableComponent} from "./timetable/timetable/timetable.component";
import {ActivityAddComponent} from "./timetable/activity/activity-add/activity-add.component";
import {ActivityEditComponent} from "./timetable/activity/activity-edit/activity-edit.component";
import {AttendanceListComponent} from "./timetable/attendance-list/attendance-list.component";
import {MemberProfileComponent} from "./person/profiles/member-profile/member-profile.component";
import {TrainerProfileComponent} from "./person/profiles/trainer-profile/trainer-profile.component";
import {EmployeeProfileComponent} from "./person/profiles/employee-profile/employee-profile.component";
import {OwnerProfileComponent} from "./person/profiles/owner-profile/owner-profile.component";
import {PassAddComponent} from "./person/pass/pass-add/pass-add.component";
import {PresenceListComponent} from "./presence/presence-list/presence-list.component";
import {PresenceAddComponent} from "./presence/presence-add/presence-add.component";

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
      {path: 'assortment/equipment/:gymId', component: AsssortmentComponent},
      {path: 'assortment/equipment/:gymId/create', component: EquipmentAddComponent},
      {path: 'assortment/equipment/:gymId/:id', component: EquipmentEditComponent},
      {path: 'timetable/activityDefinitions', component: ActivityDefinitionListComponent},
      {path: 'timetable/activityDefinitions/create', component: ActivityDefinitionAddComponent},
      {path: 'timetable/activityDefinitions/:id', component: ActivityDefinitionEditComponent},
      {path: 'timetable/activity/:gymId/:timetableId/:id/attendance', component: AttendanceListComponent},
      {path: 'timetable/activity/:gymId', component: TimetableComponent},
      {path: 'timetable/activity/:gymId/:timetableId', component: TimetableComponent},
      {path: 'timetable/activity/:gymId/:timetableId/create', component: ActivityAddComponent},
      {path: 'timetable/activity/:gymId/:timetableId/:id', component: ActivityEditComponent},
      {path: 'profile/member/:id/pass', component: PassAddComponent},
      {path: 'profile/member', component: MemberProfileComponent},
      {path: 'profile/owner', component: OwnerProfileComponent},
      {path: 'profile/employee', component: EmployeeProfileComponent},
      {path: 'profile/trainer', component: TrainerProfileComponent},
      {path: 'technical/user/add', component: TechnicalUserAddComponent},
      {path: 'people/owners', component: PersonListComponent, data: {role: Role.OWNER}},
      {path: 'people/members', component: PersonListComponent, data: {role: Role.MEMBER}},
      {path: 'people/employees', component: PersonListComponent, data: {role: Role.EMPLOYEE}},
      {path: 'people/trainers', component: PersonListComponent, data: {role: Role.TRAINER}},
      {path: 'presence/:gymId', component: PresenceListComponent},
      {path: 'presence/:gymId/create', component: PresenceAddComponent},
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
