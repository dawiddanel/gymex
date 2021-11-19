import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {GymEditComponent} from './gyms/gym-edit/gym-edit.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {GymListComponent} from './gyms/gym-list/gym-list.component';
import {GymAddComponent} from "./gyms/gym-add/gym-add.component";
import {ContactComponent} from './default/contact/contact.component';
import {GymComponent} from './gyms/gym/gym.component';
import {CountryPipe} from "./pipes/country.pipe";
import {ToastrModule} from "ngx-toastr";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {EquipmentDefinitionAddComponent} from './assortment/equipment_definition/equipment-definition-add/equipment-definition-add.component';
import {EquipmentDefinitionComponent} from './assortment/equipment_definition/equipment-definition/equipment-definition.component';
import {EquipmentDefinitionEditComponent} from './assortment/equipment_definition/equipment-definition-edit/equipment-definition-edit.component';
import {EquipmentDefinitionListComponent} from './assortment/equipment_definition/equipment-definition-list/equipment-definition-list.component';
import {EquipmentTypePipe} from "./pipes/equipmentType.pipe";
import {PurposePipe} from "./pipes/purpose.pipe";
import {BodyPartPipe} from "./pipes/bodyPart.pipe";
import {ActivityDefinitionComponent} from './timetable/activity_definition/activity-definition/activity-definition.component';
import {ActivityDefinitionAddComponent} from './timetable/activity_definition/activity-definition-add/activity-definition-add.component';
import {ActivityDefinitionEditComponent} from './timetable/activity_definition/activity-definition-edit/activity-definition-edit.component';
import {ActivityDefinitionListComponent} from './timetable/activity_definition/activity-definition-list/activity-definition-list.component';
import {LevelPipe} from "./pipes/level.pipe";
import {RolePipe} from "./pipes/role.pipe";

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
    ActivityDefinitionListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
