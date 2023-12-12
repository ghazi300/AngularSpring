import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FoyerService } from 'src/app/Services/foyer.service';
import { Bloc } from 'src/app/models/Bloc';

@Component({
  selector: 'app-list-bloc',
  templateUrl: './list-bloc.component.html',
  styleUrls: ['./list-bloc.component.css']
})
export class ListBlocComponent {
  blocs:Bloc[];

  constructor(
    private activatedRoute: ActivatedRoute,
    private foyerService: FoyerService
  ) {}
  blocImages: string[] = [
    'assets/img/bloc1.jpg',
    'assets/img/bloc2.jpg',
    'assets/img/bloc3.jpg',
  ];

  ngOnInit(): void {
    this.loadBlocs();
  }

  private loadBlocs(): void {
    // Get the nomFoyer parameter from the route
    const nomFoyer = this.activatedRoute.snapshot.params['nomFoyer'];

    // Call the service to get foyer details
    this.foyerService.getBlocs(nomFoyer).subscribe(
      (blocs: Bloc[]) => {
        this.blocs = blocs;
      },
      (error) => {
        console.error('Error fetching blocs:', error);
        // Handle error as needed
      }
    );
  }
}
