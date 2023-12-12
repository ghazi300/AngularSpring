import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FoyerRoutingModule } from './foyer-routing.module';
import { FoyerListComponent } from './foyer-list/foyer-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FoyerAddComponent } from './foyer-add/foyer-add.component';
import { FoyerUpdateComponent } from './foyer-update/foyer-update.component';


@NgModule({
  declarations: [
    FoyerListComponent,
    FoyerAddComponent,
    FoyerUpdateComponent
  ],
  imports: [
    CommonModule,
    FoyerRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class FoyerModule { }
