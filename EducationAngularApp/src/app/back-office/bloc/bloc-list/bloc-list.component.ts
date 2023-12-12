import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BlocService } from 'src/app/Services/Bloc/bloc.service';

@Component({
  selector: 'app-bloc-list',
  templateUrl: './bloc-list.component.html',
  styleUrls: ['./bloc-list.component.css']
})
export class BlocListComponent {

bloc: any ;
NotfilteredBlocs :any;
filteredBlocs:any;
nomFoyerFilter: string = '';
constructor(private es: BlocService,private ac:ActivatedRoute,private router:Router){};

ngOnInit(): void {
  this.es.getAllData().subscribe((response) => {this.bloc = response})
  this.es.getAllData().subscribe((response) => {this.filteredBlocs = response})
  this.es.getAllData().subscribe((response) => {this.NotfilteredBlocs = response})
}

delete(id:any)
{
 this.es.deleteBloc(id).subscribe(()=>{
    // this.es.getAllData().subscribe((response) => {this.etudiants = response})
   this.filteredBlocs= this.bloc.filter((bloc:any)=>bloc.idBloc!=id)
 },error =>{
   console.log(error);
 });
}



applyFilter() {
   if (this.nomFoyerFilter.trim() === '') {
    this.filteredBlocs = this.NotfilteredBlocs;
  } else {
    this.filteredBlocs = this.bloc.filter(
      (bloc: any) =>
        bloc.nomBloc.toLowerCase() === this.nomFoyerFilter.toLowerCase()
    );
  }
}

}