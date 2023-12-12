import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
 import { Observable } from 'rxjs';
import { Chambre } from 'src/app/models/Chambre';

@Injectable({
  providedIn: 'root'
})
export class ChambreService {

  constructor(private  httpClient:HttpClient) {}


  getAllData(): Observable<Chambre[]> {
    return this.httpClient.get<Chambre[]> ('http://localhost:8080/chambre/chambres');
  }

  addChambre(chambre: any) {
    return this.httpClient.post('http://localhost:8080/chambre/blocs/addChambre', chambre)
 }

 getBlocNames(): Observable<string[]> {
  return this.httpClient.get<string[]>('http://localhost:8080/chambre/blocnames');
}

deleteChambre(id:any) {
  return this.httpClient.delete('http://localhost:8080/chambre/delete/'+id);
}

editChambre(id: any,chambre:any): Observable<any> {
  return this.httpClient.put('http://localhost:8080/chambre/update/'+id, chambre);
}
getChambre(id:any) {
  return this.httpClient.get('http://localhost:8080/chambre/getId/'+id);
}

}
