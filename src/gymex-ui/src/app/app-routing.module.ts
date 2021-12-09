import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GymListComponent} from "./gyms/gym-list/gym-list.component";
import {GymAddComponent} from "./gyms/gym-add/gym-add.component";
import {GymEditComponent} from "./gyms/gym-edit/gym-edit.component";
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
import {PresenceAddComponent} from "./presence/presence-add/presence-add.component";
import {PresencesComponent} from "./presence/presences/presences.component";
import {TrainerListComponent} from "./person/trainer-list/trainer-list.component";
import {OwnerEmployeeRoleGuard} from "./security/owner-employee-role.guard";
import {MemberRoleGuard} from "./security/member-role.guard";
import {TrainerRoleGuard} from "./security/trainer-role.guard";
import {OwnerRoleGuard} from "./security/owner-role.guard";
import {EmployeeRoleGuard} from "./security/employee-role.guard";
import {QuoteComponent} from "./default/quote/quote.component";

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    canActivate: [AuthGuard],
    children: [
      {path: '', component: QuoteComponent},
      {path: 'gyms', component: GymListComponent},
      {path: 'gyms/create', component: GymAddComponent, canActivate: [OwnerEmployeeRoleGuard]},
      {path: 'gyms/:id', component: GymEditComponent, canActivate: [OwnerEmployeeRoleGuard]},
      {path: 'assortment/equipmentDefinitions', component: EquipmentDefinitionListComponent},
      {path: 'assortment/equipmentDefinitions/create', component: EquipmentDefinitionAddComponent, canActivate: [OwnerEmployeeRoleGuard]},
      {path: 'assortment/equipmentDefinitions/:id', component: EquipmentDefinitionEditComponent, canActivate: [OwnerEmployeeRoleGuard]},
      {path: 'assortment/equipment/:gymId', component: AsssortmentComponent},
      {path: 'assortment/equipment/:gymId/create', component: EquipmentAddComponent, canActivate: [OwnerEmployeeRoleGuard]},
      {path: 'assortment/equipment/:gymId/:id', component: EquipmentEditComponent, canActivate: [OwnerEmployeeRoleGuard]},
      {path: 'timetable/activityDefinitions', component: ActivityDefinitionListComponent},
      {path: 'timetable/activityDefinitions/create', component: ActivityDefinitionAddComponent, canActivate: [OwnerEmployeeRoleGuard]},
      {path: 'timetable/activityDefinitions/:id', component: ActivityDefinitionEditComponent, canActivate: [OwnerEmployeeRoleGuard]},
      {path: 'timetable/activity/:gymId/:timetableId/:id/attendance', component: AttendanceListComponent, canActivate: [OwnerEmployeeRoleGuard]},
      {path: 'timetable/activity/:gymId', component: TimetableComponent},
      {path: 'timetable/activity/:gymId/:timetableId', component: TimetableComponent},
      {path: 'timetable/activity/:gymId/:timetableId/create', component: ActivityAddComponent, canActivate: [OwnerEmployeeRoleGuard]},
      {path: 'timetable/activity/:gymId/:timetableId/:id', component: ActivityEditComponent, canActivate: [OwnerEmployeeRoleGuard]},
      {path: 'profile/member/:id/pass', component: PassAddComponent, canActivate: [MemberRoleGuard]},
      {path: 'profile/member', component: MemberProfileComponent, canActivate: [MemberRoleGuard]},
      {path: 'profile/owner', component: OwnerProfileComponent, canActivate: [OwnerRoleGuard]},
      {path: 'profile/employee', component: EmployeeProfileComponent, canActivate: [EmployeeRoleGuard]},
      {path: 'profile/trainer', component: TrainerProfileComponent, canActivate: [TrainerRoleGuard]},
      {path: 'technical/user/add', component: TechnicalUserAddComponent, canActivate: [OwnerRoleGuard]},
      {path: 'people/owners', component: PersonListComponent, data: {role: Role.OWNER}, canActivate: [OwnerRoleGuard]},
      {path: 'people/members', component: PersonListComponent, data: {role: Role.MEMBER}, canActivate: [OwnerEmployeeRoleGuard]},
      {path: 'people/employees', component: PersonListComponent, data: {role: Role.EMPLOYEE}, canActivate: [OwnerRoleGuard]},
      {path: 'people/trainers', component: TrainerListComponent},
      {path: 'presence/:gymId', component: PresencesComponent},
      {path: 'presence/:gymId/create', component: PresenceAddComponent, canActivate: [OwnerEmployeeRoleGuard]},
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
