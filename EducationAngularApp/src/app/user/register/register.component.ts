import { Component } from '@angular/core';
import {NgForm} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(private authService :AuthService ,
              private router:Router,
              private toast:ToastrService,) {
  }
  form: any={};

  onSubmit(a: NgForm) {
    console.log(a.value);
    this.authService.signup(a.value).subscribe(()=>{
      console.log("register successfully");
      this.toast.success("Register Success");
      this.router.navigate(['/user/signin']);

    },error => {
      console.log(error);

    });

  }
}
