import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import {SignInComponent} from "./sign-in/sign-in.component";
import {FormsModule} from "@angular/forms";
import { ResetComponent } from './reset/reset.component';
import { ResetPasswordDoneComponent } from './reset-password-done/reset-password-done.component';
import { RegisterComponent } from './register/register.component';


@NgModule({
  declarations: [
    UserComponent,
    SignInComponent,
    ResetComponent,
    ResetPasswordDoneComponent,
    RegisterComponent
  ],
    imports: [
        CommonModule,
        UserRoutingModule,
        FormsModule
    ]
})
export class UserModule { }
