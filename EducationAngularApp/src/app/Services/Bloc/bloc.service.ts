import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Bloc } from 'src/app/models/Bloc';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BlocService {

  constructor(private  httpClient:HttpClient) { }

  getAllData(): Observable<Bloc[]> {
    return this.httpClient.get<Bloc[]> ('http://localhost:8080/bloc/blocs');
  }
  addBloc(bloc: any) {
    return this.httpClient.post('http://localhost:8080/bloc/foyers/addBloc', bloc)
 }
 deleteBloc(id:any) {
  return this.httpClient.delete('http://localhost:8080/bloc/delete/'+id);
}

getFoyerNames(): Observable<string[]> {
  return this.httpClient.get<string[]>('http://localhost:8080/bloc/foyernames');
}
editBloc(id: any,bloc:any): Observable<any> {
  return this.httpClient.put('http://localhost:8080/bloc/update/'+id, bloc);
}
getBloc(id:any) {
  return this.httpClient.get('http://localhost:8080/bloc/getId/'+id);
}
getChambres(nomBloc:string) {
  return this.httpClient.get('http://localhost:8080/bloc/getChambres/'+nomBloc);
}

}
