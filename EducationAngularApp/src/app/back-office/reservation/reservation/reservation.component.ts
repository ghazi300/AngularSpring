import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {Chambre} from "../../../model/chambre";
import {Reservation} from "../../../model/reservation";
import {User} from "../../../service/user.service";
import {ReservationService} from "../../../service/reservation.service";
import {Router} from "@angular/router";
import { jsPDF } from "jspdf";

import 'jspdf-autotable';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html'
})
export class ReservationComponent implements OnInit {

  Reservations: any = [];
  notReservedRooms: Chambre[] = [];
  searchText;
  p:number = 1 ;
  POSTS: any;
  page: number = 1;
  count: number = 0;
  tableSize: number = 4;
  tableSizes: any = [5, 10, 15, 20];


  constructor(private reservationService:ReservationService,private router:Router){}


  private getReservation() {
    this.reservationService.getListReservation().subscribe(data=> {
      console.log(data);
      this.Reservations = data.map((reservation: any) => {
        // Validate if the date string is valid
        const isValidDate = !isNaN(Date.parse(reservation.anneeUniversitaire));
        if (isValidDate) {
          reservation.anneeUniversitaire = new Date(reservation.anneeUniversitaire);
        } else {
          // Handle invalid date here, for example set it to null or another default value
          reservation.anneeUniversitaire = null;
        }
        return reservation;
      });
      // this.Reservations=data;
    })}

  deleteReservation(idReservation:number){

    this.reservationService.deleteReservation(idReservation).subscribe( data => {
      console.log(data);
      this.getReservation();
    })
  }
  postList(): void {
    this.reservationService.getListReservation().subscribe((response) => {
      this.POSTS = response;
      console.log(this.POSTS);
    });
  }
  onTableDataChange(event: any) {
    this.page = event;
    this.postList();
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.postList();

  }



  printSimplePdf() {

    const doc = new jsPDF({

        orientation: 'landscape',
        unit: 'in',
        format: [4, 8]
      }

    );

    // En-tête du tableau
    const headers = ['Annee Universaire', 'Numero de Chambre' , 'Type de Chambre' , 'Cin Etudiant' , 'Choix Reservation'];

    // Données des étudiants
    const data = this.Reservations.map(Reservations => [
      Reservations.anneeUniversaire,
      Reservations.chambre.numeroChambre,
      Reservations.chambre.typeChambre,
      Reservations.etudiant.email,


    ]);

    (doc as any).autoTable({

      head: [headers],
      body: data
    });

    doc.save('etudiants.pdf');
  }
  ngOnInit(): void {
    this.getReservation();
  }

}
