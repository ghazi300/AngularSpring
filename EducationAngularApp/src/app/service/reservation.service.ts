
import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Reservation} from "../model/reservation";
import {Chambre} from "../model/chambre";
import {User} from "./user.service";


@Injectable({
  providedIn: 'root'
})

export class ReservationService {

  private baseURL="http://localhost:8080/api/v1/reservation";
  private baseURLNot="http://localhost:8080/api/v1/reservation/not-reserved";
  private baseURLEtud="http://localhost:8080/api/v1/reservation/students-without-reservation";
  private baseURLGet="http://localhost:8080/api/v1/reservation/update";
  private baseURLGetId="http://localhost:8080/api/v1/reservation/getBy";
  private baseURLGetSupp="http://localhost:8080/api/v1/reservation/delete";


  constructor(private httpClient:HttpClient) { }



  getListReservation(): Observable <any>{

    return this.httpClient.get<any>(`${this.baseURL} `);
  }
  createReservation(reservation : any): Observable<Object>{

    return this.httpClient.post(`${this.baseURL}`,reservation);
  }

  getNotReservedRooms(): Observable<Chambre[]> {
    return this.httpClient.get<Chambre[]>(`${this.baseURLNot}`);
  }


  getEtudiantWithoutReservation(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.baseURLEtud}`);

  }
  getReservationById(idReservation:number): Observable<Reservation>{
    return this.httpClient.get<Reservation>(`${this.baseURLGetId}/${idReservation}`);
  }
  updateReservation(idReservation:number,Reservation):Observable<Object>{
    return this.httpClient.put(`${this.baseURLGet}/${idReservation}`,Reservation);

  }

  deleteReservation(idReservation: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURLGetSupp}/${idReservation}`);
  }












}
