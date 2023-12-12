import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ChambreService } from 'src/app/Services/Chambre/chambre.service';
import { TypeChambre } from 'src/app/model/TypeChambre.enum';
import { trigger, style, animate, transition, state } from '@angular/animations';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-chambre',
  templateUrl: './add-chambre.component.html',
  styleUrls: ['./add-chambre.component.css'],
  animations: [
    trigger('buttonAnimation', [
      state('enabled', style({
        transform: 'translateX(0)',
      })),
      state('left', style({
        transform: 'translateX(-100px)', // Adjust the value based on your preference
      })),

      state('right', style({
        transform: 'translateX(150px)', // Adjust the value based on your preference
      })),

      transition('* => left', animate('200ms ease-out')),
      transition('* => right', animate('200ms ease-out')),
      transition('* => enabled', animate('200ms ease-out')),
    ]),
  ],
})
export class AddChambreComponent {

TypeChambre = TypeChambre ;



  blocNames: string[] = [];
  selectedBloc: string = "Select Bloc";
  form: FormGroup;
selectedType: any;


  constructor(private es: ChambreService, private router: Router,private fb: FormBuilder) {   this.form = this.fb.group({
    typeChambre: ['', Validators.required]
  }); }
  ngOnInit(): void {this.fetchBlocNames()}


  addChambre(chambreData: any) {

    console.log("bloc data :"+chambreData);
    this.es.addChambre(chambreData).subscribe(
      () => {
        // Handle a successful response (status code 200) here
        this.router.navigate(['back/chambre/chambrelist']);
      },
      (error: HttpErrorResponse) => {
        // Handle the error here
        if (error.status === 200) {
          // Consider a status code of 200 as success and navigate
          this.router.navigate(['back/chambre/chambrelist']);
        } else {
          console.error('Error adding Etudiant:', error);
          // Handle the error as needed, e.g., display an error message to the user
        }
      }
    );
  }

  isTypeInvalid() {
    const control = this.form.get('typeChambre');
    return control?.hasError('required') && control.touched;
  }

  fetchBlocNames() {
    this.es.getBlocNames().subscribe(
      names => {
        this.blocNames = names;
      },
      error => {
        console.error('Error fetching foyer names:', error);
      }
    );
  }




  buttonState = 'enabled';

  onMouseEnter() {

    if( this.buttonState =='enabled')
      this.buttonState ='right'

    else if(this.buttonState =='right')
      this.buttonState = 'left';

    else if(this.buttonState =='left')
      this.buttonState = 'right';



    }

  onMouseLeave() {
    if( this.buttonState =='left')
      this.buttonState ='left'

     else if(this.buttonState =='right')
      this.buttonState = 'right';
  }




}