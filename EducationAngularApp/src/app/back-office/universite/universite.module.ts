import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UniversiteRoutingModule } from './universite-routing.module';
import { UniversiteListComponent } from './universite-list/universite-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UniversiteAddComponent } from './universite-add/universite-add.component';
import { UniversiteUpdateComponent } from './universite-update/universite-update.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    UniversiteListComponent,
    UniversiteAddComponent,
    UniversiteUpdateComponent
  ],
  imports: [
    CommonModule,
    UniversiteRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
  ]
})
export class UniversiteModule { }
