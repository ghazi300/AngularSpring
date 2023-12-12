import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UniversiteListComponent } from './universite-list/universite-list.component';
import { UniversiteAddComponent } from './universite-add/universite-add.component';
import { UniversiteUpdateComponent } from './universite-update/universite-update.component';

const routes: Routes = [
  {path:"universiteList", component:UniversiteListComponent},
  {path:"add", component:UniversiteAddComponent},
  {path:"edit/:id", component:UniversiteUpdateComponent},
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UniversiteRoutingModule { }
