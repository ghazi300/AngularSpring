import { Component, OnInit } from '@angular/core';
import { Foyer } from 'src/app/models/foyer';
import { FoyerService } from 'src/app/Services/foyer.service';
import { ActivatedRoute } from '@angular/router';
import { Bloc } from 'src/app/models/Bloc';
import { trigger, transition, animate, style } from '@angular/animations';

@Component({
  selector: 'app-foyer-details',
  templateUrl: './foyer-details.component.html',
  styleUrls: ['./foyer-details.component.css'],
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('500ms', style({ opacity: 1 })),
      ]),
      transition(':leave', [
        animate('500ms', style({ opacity: 0 })),
      ]),
    ]),
  ],
})
export class FoyerDetailsComponent implements OnInit {
  foyerDetails: Foyer;
  blocs: Bloc[];

  constructor(
    private activatedRoute: ActivatedRoute,
    private foyerDetailsService: FoyerService
  ) {}

  ngOnInit(): void {
    this.loadFoyerDetails();
  }

  private loadFoyerDetails(): void {
    const nomFoyer = this.activatedRoute.snapshot.params['nomFoyer'];
    this.foyerDetailsService.getFoyerDetails(nomFoyer).subscribe(
      (details: Foyer) => {
        this.foyerDetails = details;
      },
      (error) => {
        console.error('Error fetching foyer details:', error);
      }
    );
  }
}
