import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Universite } from 'src/app/models/universite';
import { UniversiteService } from 'src/app/Services/universite.service';
@Component({
  selector: 'app-university-cart',
  templateUrl: './university-cart.component.html',
  styleUrls: ['./university-cart.component.css']
})
export class UniversityCartComponent {
  universites: Universite[] = [];
  searchTerm: string = '';
  foyerNames: string[] = [];

  constructor(private es: UniversiteService , private router:Router){};
  universityImages: string[] = [
    'assets/img/20210304132220.puTaOn.png',
    'assets/img/Ihec.jpg',
    'assets/img/insat_logo.png',
    'assets/img/esprit.png',
    'assets/img/MSB.jpg',
    'assets/img/Dauphine.jpg'
  ];


ngOnInit(): void {
  this.loadUniversites();
  this.loadFoyerNames();
}
loadUniversites(){
  this.es.getAllUniversites().subscribe(
    (universites: Universite[]) => {
      this.universites = universites;
    },
    (error) => {
      console.error('Erreur lors de la récupération des universités :', error);
    }
  );
}
applyFilter(): Universite[] {
  return this.universites.filter(universite =>
    universite.nomUniversite.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
    universite.adresse.toLowerCase().includes(this.searchTerm.toLowerCase())
  );
}

loadFoyerNames(): void {
  this.es.getFoyerNames().subscribe(
    (foyerNames: string[]) => {
      this.foyerNames = foyerNames;
    },
    (error) => {
      console.error('Erreur lors de la récupération des noms de foyers :', error);
    }
  );
}

}
