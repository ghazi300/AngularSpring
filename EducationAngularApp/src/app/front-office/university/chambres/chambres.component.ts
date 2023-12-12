import { Component } from '@angular/core';
import { Chambre } from 'src/app/model/chambre';
import { ActivatedRoute, Route } from '@angular/router';
import { BlocService } from 'src/app/Services/Bloc/bloc.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { AuthService } from 'src/app/service/auth.service';
import { Toast, ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chambres',
  templateUrl: './chambres.component.html',
  styleUrls: ['./chambres.component.css']
})
export class ChambresComponent {
  chambres:Chambre[];
  CurrentUser:any;



  

  constructor(
    private activatedRoute: ActivatedRoute,
    private blocService: BlocService,
    private reservationService:ReservationService,
    private authService:AuthService,
    private router:Router,
    private toast:ToastrService
  ) {}
  chambresImages: string[] = [
    'assets/img/chambre1.jpg',
    'assets/img/chambre2.jpg',
    'assets/img/chambre3.jpg',
  ];

  ngOnInit(): void {
    console.log("blocccccccccccccc",this.activatedRoute.params['nomBloc']);
    this.loadChambres();
    this.getCurrentUser();
  }

  private loadChambres(): void {
    
    // Get the nomFoyer parameter from the route
    const nomBloc = this.activatedRoute.snapshot.params['nomBloc'];

    // Call the service to get foyer details
    this.blocService.getChambres(nomBloc).subscribe(
      (chambres: any) => {
        this.chambres = chambres;
      },
      (error) => {
        console.error('Error fetching chambres:', error);
        // Handle error as needed
      }
    );
  }
  reserver(idChambre:any) {
    
    const requestBody = {
      anneeUniversitaire: new Date(),
      chambreId: idChambre, // Assuming 'id' is the identifier for chambre
     userId:this.CurrentUser.id  // Assuming 'id' is the identifier for user
    };

    this.reservationService.createReservation(requestBody).subscribe(()=>{
      console.log("success");
      this.toast.success("Reservation avec success !" );
      this.router.navigate(['/front/home']);


    },error => {
      console.log(error);

    });
    
   
    
  }
  private getCurrentUser() {
    this.authService.getCurrentuser().subscribe((data:any)=>{
      this.CurrentUser=data;
      console.log("User",this.CurrentUser);
     

    },error => {
      console.log(error);
    });
  }
}

