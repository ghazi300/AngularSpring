import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BlocService } from 'src/app/Services/Bloc/bloc.service';
import { ChambreService } from 'src/app/Services/Chambre/chambre.service';
import { Chambre } from 'src/app/model/chambre';

@Component({
  selector: 'app-edit-chambre',
  templateUrl: './edit-chambre.component.html',
  styleUrls: ['./edit-chambre.component.css']
})
export class EditChambreComponent {


  idChambre!:Number;
  chambre!:any;
  updateForm!:FormGroup;
  blocNames: string[] = [];


  constructor(private formBuilder: FormBuilder, private actRoute:ActivatedRoute,private es: ChambreService, private router: Router) { }
  ngOnInit(): void {
    this.getParam();
    this.fetchBlocNames();
    this.es.getChambre(this.idChambre).subscribe((data)=>{
      this.chambre=data
      this.updateForm=new FormGroup({
         numChambre:new FormControl(this.chambre.numChambre),
        typeChambre:new FormControl(this.chambre.typeChambre),
        nomBloc:new FormControl(this.chambre.nomBloc),
       });
     });
  }

  update(){
      const updatedData = this.updateForm.value;
      this.es.editChambre(this.idChambre,updatedData).subscribe(
        ()=>this.router.navigate(['back/chambre/chambrelist'])
      );
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
  getParam(){
    //this.id= Number( this.actR.snapshot.paramMap.get('param'));
    this.actRoute.paramMap.subscribe(data => this.idChambre=Number(data.get('param')));
   }
}
