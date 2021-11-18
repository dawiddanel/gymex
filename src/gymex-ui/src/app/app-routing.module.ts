import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {GymListComponent} from "./gyms/gym-list/gym-list.component";
import {GymAddComponent} from "./gyms/gym-add/gym-add.component";
import {GymEditComponent} from "./gyms/gym-edit/gym-edit.component";
import {ContactComponent} from "./default/contact/contact.component";

const routes: Routes = [
  { path: '', redirectTo: 'gyms', pathMatch: 'full' },
  { path: 'gyms', component: GymListComponent },
  { path: 'gyms/create', component: GymAddComponent },
  { path: 'gyms/:id', component: GymEditComponent },
  { path: 'contact', component: ContactComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
