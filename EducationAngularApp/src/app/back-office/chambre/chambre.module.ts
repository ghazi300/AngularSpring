import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChambreRoutingModule } from './chambre-routing.module';
import { ChambreListComponent } from './chambre-list/chambre-list.component';
import { AddChambreComponent } from './add-chambre/add-chambre.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EditChambreComponent } from './edit-chambre/edit-chambre.component';


@NgModule({
  declarations: [
    ChambreListComponent,
    AddChambreComponent,
    EditChambreComponent
  ],
  imports: [
    CommonModule,
    ChambreRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ChambreModule { }
