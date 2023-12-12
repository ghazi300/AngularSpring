import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UniversityCartComponent } from './university-cart/university-cart.component';
import { FoyerDetailsComponent } from './foyer-details/foyer-details.component';
import { ListBlocComponent } from './list-bloc/list-bloc.component';
import { Chambre } from 'src/app/model/chambre';
import { ChambresComponent } from './chambres/chambres.component';

const routes: Routes = [
  {path:"university",component:UniversityCartComponent},
  { path: "foyer-details/:nomFoyer", component: FoyerDetailsComponent },
  { path: "list-bloc/:nomFoyer", component: ListBlocComponent },
  { path: "chambres/:nomBloc", component: ChambresComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UniversityRoutingModule { }
