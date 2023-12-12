import { Component, OnInit } from '@angular/core';
import { UniversiteService } from 'src/app/Services/universite.service';
import { FoyerService } from 'src/app/Services/foyer.service';
import { ActivatedRoute } from '@angular/router';
import { Foyer } from 'src/app/models/foyer';
import { Universite } from 'src/app/models/universite';
import { Router } from '@angular/router';
@Component({
  selector: 'app-foyer-list',
  templateUrl: './foyer-list.component.html',
  styleUrls: ['./foyer-list.component.css']
})
export class FoyerListComponent {
  foyers: Foyer[] = [];
  searchTerm: string = '';
  constructor(private es: FoyerService){};



  // Modifier la méthode ngOnInit pour appliquer le filtre
ngOnInit(): void {
  this.loadFoyers();
}
loadFoyers(){
  this.es.getAllFoyers().subscribe(
    (foyers: Foyer[]) => {
      this.foyers = foyers;
    },
    (error) => {
      console.error('Erreur lors de la récupération des foyers :', error);
    }
  );
}
applyFilter(): Foyer[] {
  return this.foyers.filter(foyer =>
    foyer.nomFoyer.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
    (foyer.capaciteFoyer !== null && foyer.capaciteFoyer.toString().includes(this.searchTerm.toLowerCase()))
  );
}


delete(idFoyer: number): void {
  this.es.deleteFoyer(idFoyer).subscribe(
    () => {
      console.log('Foyer supprimée avec succès !');
      // Update the list of universities after deletion
      this.loadFoyers();
    },
    (error) => {
      console.error('Erreur lors de la suppression du foyer :', error);
    }
  );
}

}


