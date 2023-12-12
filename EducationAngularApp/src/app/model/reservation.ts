
import { Chambre } from 'src/app/model/chambre';
import {User} from "../service/user.service";



export class Reservation {



  idReservation: number;
  anneeUniversaire: Date;
  chambre: Chambre;
  etudiant: { id: number; email: String; username: String };

}

