import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Universite } from '../models/universite';
import { Foyer } from '../models/foyer';

@Injectable({
  providedIn: 'root'
})
export class UniversiteService {
  private apiUrl = '/universite'; // À adapter selon votre backend

  constructor(private http: HttpClient) {}

  addUniversite(universiteData: FormData): Observable<Universite> {
    return this.http.post<Universite>(`${this.apiUrl}/new`, universiteData);
  }
  
  updateUniversite(id: number, universite: Universite): Observable<Universite> {
    return this.http.put<Universite>(`${this.apiUrl}/update/${id}`, universite);
  }

  getUniversiteById(id: number): Observable<Universite> {
    return this.http.get<Universite>(`${this.apiUrl}/getId/${id}`);
  }
  getFoyerDetails(foyerId: number): Observable<Foyer> {
    return this.http.get<Foyer>(`${this.apiUrl}/getId/${foyerId}`);
  }

  getAllUniversites(): Observable<Universite[]> {
    return this.http.get<Universite[]>(`${this.apiUrl}/getAll`);
  }

  deleteUniversite(id: number): Observable<any> {
    const url = `${this.apiUrl}/delete/${id}`;
    return this.http.delete(url, { responseType: 'text' });
  }
  getFoyerNames(): Observable<string[]> {
    const url = `${this.apiUrl}/foyernames`;
    return this.http.get<string[]>(url);
  }

  affecterFoyerAUniversite(nomFoyer: string, idUniversite: number): Observable<any> {
    const url = `${this.apiUrl}/foyers`;
    const params = { nomFoyer, idUniversite };
    
    return this.http.post(url, null, { params });
  }

  desaffecterFoyerAUniversite(idUniv: number): Observable<Universite> {
    const url = `${this.apiUrl}/desaffecterFoyer`;
    const params = { idUniv: idUniv.toString() };
    return this.http.patch<Universite>(url, null, { params });
  }
  checkUniqueName(nomUniversite: string): Observable<boolean> {
    const url = `${this.apiUrl}/checkUniqueName?nomUniversite=${nomUniversite}`;
    return this.http.get<boolean>(url);
  }
}
