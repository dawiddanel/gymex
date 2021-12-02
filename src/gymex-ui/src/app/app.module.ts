import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {GymEditComponent} from './gyms/gym-edit/gym-edit.component';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {GymListComponent} from './gyms/gym-list/gym-list.component';
import {GymAddComponent} from "./gyms/gym-add/gym-add.component";
import {ContactComponent} from './default/contact/contact.component';
import {GymComponent} from './gyms/gym/gym.component';
import {CountryPipe} from "./default/pipes/country.pipe";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {EquipmentDefinitionAddComponent} from './assortment/equipment_definition/equipment-definition-add/equipment-definition-add.component';
import {EquipmentDefinitionComponent} from './assortment/equipment_definition/equipment-definition/equipment-definition.component';
import {EquipmentDefinitionEditComponent} from './assortment/equipment_definition/equipment-definition-edit/equipment-definition-edit.component';
import {EquipmentDefinitionListComponent} from './assortment/equipment_definition/equipment-definition-list/equipment-definition-list.component';
import {EquipmentTypePipe} from "./default/pipes/equipmentType.pipe";
import {PurposePipe} from "./default/pipes/purpose.pipe";
import {BodyPartPipe} from "./default/pipes/bodyPart.pipe";
import {ActivityDefinitionComponent} from './timetable/activity_definition/activity-definition/activity-definition.component';
import {ActivityDefinitionAddComponent} from './timetable/activity_definition/activity-definition-add/activity-definition-add.component';
import {ActivityDefinitionEditComponent} from './timetable/activity_definition/activity-definition-edit/activity-definition-edit.component';
import {ActivityDefinitionListComponent} from './timetable/activity_definition/activity-definition-list/activity-definition-list.component';
import {LevelPipe} from "./default/pipes/level.pipe";
import {RolePipe} from "./default/pipes/role.pipe";
import { RegisterComponent } from './security/register/register.component';
import { LoginComponent } from './security/login/login.component';
import { MainNavbarComponent } from './default/main-navbar/main-navbar.component';
import {JwtInterceptor} from "./security/jwt.interceptor";
import { MainComponent } from './default/main/main.component';
import { EquipmentComponent } from './assortment/equipment/equipment/equipment.component';
import { EquipmentAddComponent } from './assortment/equipment/equipment-add/equipment-add.component';
import { EquipmentEditComponent } from './assortment/equipment/equipment-edit/equipment-edit.component';
import { AsssortmentComponent } from './assortment/assortment/asssortment.component';
import { PersonComponent } from './person/person/person.component';
import { UserComponent } from './person/user/user.component';
import { ProfileComponent } from './person/profile/profile.component';
import { TechnicalUserAddComponent } from './person/technical-user-add/technical-user-add.component';
import { PersonListComponent } from './person/person-list/person-list.component';
import { ActivityComponent } from './timetable/activity/activity/activity.component';
import { ActivityAddComponent } from './timetable/activity/activity-add/activity-add.component';
import { ActivityEditComponent } from './timetable/activity/activity-edit/activity-edit.component';
import { TimetableComponent } from './timetable/timetable/timetable.component';
import { EquipmentListComponent } from './assortment/equipment/equipment-list/equipment-list.component';
import { ActivityListComponent } from './timetable/activity/activity-list/activity-list.component';
import { AttendanceComponent } from './timetable/attendance/attendance.component';
import { AttendanceListComponent } from './timetable/attendance-list/attendance-list.component';

@NgModule({
  declarations: [
    AppComponent,
    GymEditComponent,
    GymAddComponent,
    GymListComponent,
    ContactComponent,
    GymComponent,
    CountryPipe,
    EquipmentTypePipe,
    PurposePipe,
    BodyPartPipe,
    LevelPipe,
    RolePipe,
    EquipmentDefinitionAddComponent,
    EquipmentDefinitionComponent,
    EquipmentDefinitionEditComponent,
    EquipmentDefinitionListComponent,
    ActivityDefinitionComponent,
    ActivityDefinitionAddComponent,
    ActivityDefinitionEditComponent,
    ActivityDefinitionListComponent,
    RegisterComponent,
    LoginComponent,
    MainNavbarComponent,
    MainComponent,
    EquipmentComponent,
    EquipmentAddComponent,
    EquipmentEditComponent,
    AsssortmentComponent,
    PersonComponent,
    UserComponent,
    ProfileComponent,
    TechnicalUserAddComponent,
    PersonListComponent,
    ActivityComponent,
    ActivityAddComponent,
    ActivityEditComponent,
    TimetableComponent,
    EquipmentListComponent,
    ActivityListComponent,
    AttendanceComponent,
    AttendanceListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
