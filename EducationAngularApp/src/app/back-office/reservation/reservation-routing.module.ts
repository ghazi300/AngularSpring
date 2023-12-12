import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ReservationComponent } from './reservation/reservation.component';
import {AddReservationComponent} from "./add-reservation/add-reservation.component";
import {UpdateReservationComponent} from "./update-reservation/update-reservation.component";
import { ChartDemoComponent } from './chart-demo/chart-demo.component';


const routes: Routes = [
  {path:"list", component:ReservationComponent},
  {path:'list/add',component: AddReservationComponent},

  {path:'list/chart',component: ChartDemoComponent},

  { path:'list/update-reservation/:idReservation', component: UpdateReservationComponent },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReservationRoutingModule {


 }
