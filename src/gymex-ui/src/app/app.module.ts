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
import { EquipmentDefinitionAddComponent } from './assortment/equipment_definition/equipment-definition-add/equipment-definition-add.component';
import { EquipmentDefinitionComponent } from './assortment/equipment_definition/equipment-definition/equipment-definition.component';
import { EquipmentDefinitionEditComponent } from './assortment/equipment_definition/equipment-definition-edit/equipment-definition-edit.component';
import { EquipmentDefinitionListComponent } from './assortment/equipment_definition/equipment-definition-list/equipment-definition-list.component';
import {EquipmentTypePipe} from "./pipes/equipmentType.pipe";
import {PurposePipe} from "./pipes/purpose.pipe";
import {BodyPartPipe} from "./pipes/bodyPart.pipe";

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
    EquipmentDefinitionAddComponent,
    EquipmentDefinitionComponent,
    EquipmentDefinitionEditComponent,
    EquipmentDefinitionListComponent
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
