
import {Component, OnInit} from '@angular/core';
import {Chambre} from "../../../model/chambre";
import {User} from "../../../service/user.service";
import {Reservation} from "../../../model/reservation";
import {ReservationService} from "../../../service/reservation.service";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";


@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html',
  styleUrls: ['./add-reservation.component.css']
})

export class AddReservationComponent implements OnInit{
  selectedChambre: Chambre = null; // Declare selectedUniversite property
  selectedEtudiant: User=null; // Declare selectedUniversite property

  notReservedRooms: Chambre[] = [];
  EtudiantWithoutReservation: User[] = [];



  Reservations: Reservation ={
    idReservation:0,
    anneeUniversaire:null,


    chambre : null,
    etudiant : null,



  };
  constructor(private reservationService:ReservationService,private router:Router){}


  ajouterReservation() {
    const requestBody = {
      anneeUniversitaire: this.Reservations.anneeUniversaire,
      chambreId: this.selectedChambre.idChambre, // Assuming 'id' is the identifier for chambre
      userId: this.selectedEtudiant.id // Assuming 'id' is the identifier for user
    };

    console.log(requestBody);










    this.reservationService.createReservation(requestBody).subscribe(
      () => {
        console.log("Rservation ajouter avec succées !")
        this.router.navigate(['/back/reservation/list']);
      },
      (error: HttpErrorResponse) => { // Specify the type as HttpErrorResponse
        console.error('Error adding Reservation:', error);
      }
    );

  }
  getNotReservedRooms(): void {
    this.reservationService.getNotReservedRooms().subscribe(
      (data) => {
        this.notReservedRooms = data;
        console.log(this.notReservedRooms)
      },
      (error) => {
        console.error('Error fetching not reserved rooms:', error);
      }
    );
  }

  getEtudiantWithoutReservation(): void {
    this.reservationService.getEtudiantWithoutReservation().subscribe(
      (data) => {
        this.EtudiantWithoutReservation = data;
        console.log("etudiant get all",this.EtudiantWithoutReservation)
      },
      (error) => {
        console.error('Error fetching not etudiants:', error);
      }
    );
  }


  ngOnInit(): void {
    this.getNotReservedRooms()
    this.getEtudiantWithoutReservation()

  }
  retour(){
    this.router.navigate(['/back/reservation/list']);

  }

}
