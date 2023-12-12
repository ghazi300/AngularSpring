import { Component, OnInit } from '@angular/core';
import { UniversiteService } from 'src/app/Services/universite.service';
import { FoyerService } from 'src/app/Services/foyer.service';
import { ActivatedRoute } from '@angular/router';
import { Foyer } from 'src/app/models/foyer';
import { Universite } from 'src/app/models/universite';
import { Router } from '@angular/router';

@Component({
  selector: 'app-universite-list',
  templateUrl: './universite-list.component.html',
  styleUrls: ['./universite-list.component.css']
})
export class UniversiteListComponent implements OnInit {
  universites: Universite[] = [];
  searchTerm: string = '';
  foyerNames: string[] = [];

  constructor(private es: UniversiteService){};


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

delete(idUniversite: number): void {
  this.es.deleteUniversite(idUniversite).subscribe(
    () => {
      console.log('Université supprimée avec succès !');
      // Update the list of universities after deletion
      this.loadUniversites();
    },
    (error) => {
      console.error('Erreur lors de la suppression de l\'université :', error);
    }
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

affecterFoyer(nomFoyer: string, idUniversite: number): void {
  this.es.affecterFoyerAUniversite(nomFoyer, idUniversite).subscribe(
    () => {
      console.log('Foyer affecté avec succès !');
      // Mise à jour de la liste des universités après l'affectation
      this.loadUniversites();
      this.loadFoyerNames();
    },
    (error) => {
      console.error('Erreur lors de l\'affectation du foyer :', error);
    }
  );
}


desaffecterFoyer(idUniversite: number): void {
  this.es.desaffecterFoyerAUniversite(idUniversite).subscribe(
    (universite) => {
      console.log('Foyer désaffecté avec succès !', universite);
      // Mise à jour de la liste des universités après la désaffectation
      this.loadUniversites();
      this.loadFoyerNames();
    },
    (error) => {
      console.error('Erreur lors de la désaffectation du foyer :', error);
    }
  );
}

}
