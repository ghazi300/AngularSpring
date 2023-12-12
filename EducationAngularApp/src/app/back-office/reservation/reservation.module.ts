import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReservationRoutingModule } from './reservation-routing.module';

import { ReservationComponent } from './reservation/reservation.component';
import {FormsModule} from "@angular/forms";
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import {Ng2SearchPipeModule} from "ng2-search-filter";
import {NgxPaginationModule} from "ngx-pagination";
import { UpdateReservationComponent } from './update-reservation/update-reservation.component';


@NgModule({
  declarations: [
    ReservationComponent,
    AddReservationComponent,
    UpdateReservationComponent
  ],
  imports: [
    CommonModule,
    ReservationRoutingModule,
    FormsModule,

    NgxPaginationModule,
    Ng2SearchPipeModule,

  ]
})
export class ReservationModule { }
