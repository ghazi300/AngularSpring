import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BlocService } from 'src/app/Services/Bloc/bloc.service';
import { trigger, style, animate, transition, state } from '@angular/animations';

@Component({
  selector: 'app-add-bloc',
  templateUrl: './add-bloc.component.html',
  styleUrls: ['./add-bloc.component.css'],
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
export class AddBlocComponent {
  foyerNames: string[] = [];
  selectedFoyer: string = "Select Foyer";


  constructor(private es: BlocService, private router: Router) { }
  ngOnInit(): void {this.fetchFoyerNames()}


  addBloc(blocData: any) {

    console.log("bloc data :"+blocData);
    this.es.addBloc(blocData).subscribe(
      () => {
        // Handle a successful response (status code 200) here
        this.router.navigate(['back/bloc/bloclist']);
      },
      (error: HttpErrorResponse) => {
        // Handle the error here
        if (error.status === 200) {
          // Consider a status code of 200 as success and navigate
          this.router.navigate(['back/bloc/bloclist']);
        } else {
          console.error('Error adding Etudiant:', error);
          // Handle the error as needed, e.g., display an error message to the user
        }
      }
    );
  }


  fetchFoyerNames() {
    this.es.getFoyerNames().subscribe(
      names => {
        this.foyerNames = names;
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
