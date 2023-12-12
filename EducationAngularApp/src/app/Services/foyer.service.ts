import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Foyer } from '../models/foyer';
import { Bloc } from '../models/Bloc';

@Injectable({
  providedIn: 'root'
})
export class FoyerService {
  private apiUrl = '/foyer'; 

  constructor(private http: HttpClient) {}
  getFoyerDetails(nomFoyer: string): Observable<Foyer> {
    const url = `${this.apiUrl}/getFoyer?nomFoyer=${nomFoyer}`;
    return this.http.get<Foyer>(url);
  }

  addFoyer(foyer: Foyer): Observable<Foyer> {
    return this.http.post<Foyer>(`${this.apiUrl}/new`, foyer);
  }

  updateFoyer(id: number, foyer: Foyer): Observable<Foyer> {
    return this.http.put<Foyer>(`${this.apiUrl}/update/${id}`, foyer);
  }

  getFoyerById(id: number): Observable<Foyer> {
    return this.http.get<Foyer>(`${this.apiUrl}/getId/${id}`);
  }

  getAllFoyers(): Observable<Foyer[]> {
    return this.http.get<Foyer[]>(`${this.apiUrl}/getAll`);
  }

  deleteFoyer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
  checkUniqueName(nomFoyer: string): Observable<boolean> {
    const url = `${this.apiUrl}/checkUniqueName?nomFoyer=${nomFoyer}`;
    return this.http.get<boolean>(url);
  }
  getBlocs(nomFoyer: string): Observable<Bloc[]> {
    const url = `${this.apiUrl}/getBlocs?nomFoyer=${nomFoyer}`;
    return this.http.get<Bloc[]>(url);
  }

}
