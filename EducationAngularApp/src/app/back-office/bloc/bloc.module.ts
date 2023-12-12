import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BlocRoutingModule } from './bloc-routing.module';
import { BlocListComponent } from './bloc-list/bloc-list.component';
import { EditBlocComponent } from './edit-bloc/edit-bloc.component';
import { AddBlocComponent } from './add-bloc/add-bloc.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    BlocListComponent,
    EditBlocComponent,
    AddBlocComponent,

  ],
  imports: [
    CommonModule,
    BlocRoutingModule,
        FormsModule,
    ReactiveFormsModule
  ]
})
export class BlocModule { }
