import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FoyerListComponent } from './foyer-list/foyer-list.component';
import { FoyerAddComponent } from './foyer-add/foyer-add.component';
import { FoyerUpdateComponent } from './foyer-update/foyer-update.component';

const routes: Routes = [
  {path:"foyerList", component:FoyerListComponent},
  {path:"add", component:FoyerAddComponent},
  {path:"edit/:id", component:FoyerUpdateComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FoyerRoutingModule { }
