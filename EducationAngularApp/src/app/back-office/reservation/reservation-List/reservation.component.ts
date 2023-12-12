import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Chambre } from 'src/app/model/chambre';
import { Reservation } from 'src/app/model/reservation';
import { ReservationService } from 'src/app/service/reservation.service';
import { jsPDF } from "jspdf";

import 'jspdf-autotable';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  Reservations: Reservation[] = [];
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
      this.Reservations=data;
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
        Reservations.etudiant.cin,
        Reservations.choixReservation,
  
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





  
  


