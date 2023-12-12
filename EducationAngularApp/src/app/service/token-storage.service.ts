import { Injectable } from '@angular/core';

const TOKEN_KEY = 'token';
const USER_KEY = 'auth-user';

const ROLE_KEY = 'userRoles';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {


  constructor() { }
  signOut(): void {
    localStorage.clear();
  }
  public saveToken(token: string): void {
   localStorage.removeItem(TOKEN_KEY);
    localStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    const token= localStorage.getItem(TOKEN_KEY);

    return token !==null ? token :'';
  }
  public getRole() {
    const roles = localStorage.getItem(ROLE_KEY);
    if (roles) {
      return JSON.parse(roles);
    }

    return '';

  }
}
