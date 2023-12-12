import { Component } from '@angular/core';
import {NgForm} from "@angular/forms";
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.css']
})
export class ResetComponent {
  form: any={};
  constructor(private authService:AuthService,private _router:Router) {
  }


  onSubmit(a: NgForm) {
   this.authService.getOtp(this.form).subscribe(()=>{
     this._router.navigate(['user/reset-password-done']);

   },error => {
     console.log(error);

   });

  }
}
