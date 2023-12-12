import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BackOfficeComponent } from './back-office.component';
import { HomeBackComponent } from './home-back/home-back.component';
import {AdminGuard} from "../helper/admin.guard";

const routes: Routes = [
  {path: '',
    canActivate:[AdminGuard],
    component: BackOfficeComponent,
    children: [
      {path:"bloc",loadChildren:()=> import('./bloc/bloc.module').then(m=>m.BlocModule)},
       {path:"universite",loadChildren:()=> import('./universite/universite.module').then(m=>m.UniversiteModule)},
        {path:"foyer",loadChildren:()=> import('./foyer/foyer.module').then(m=>m.FoyerModule)},
      {path:"chambre",loadChildren:()=>   import('./chambre/chambre.module').then(m=>m.ChambreModule)},
      {path:"reservation",loadChildren:()=>   import('./reservation/reservation.module').then(m=>m.ReservationModule)},
      {path:"dashboard", component:HomeBackComponent}

]}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BackOfficeRoutingModule { }
