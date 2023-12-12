import { Injectable } from '@angular/core';

export interface User {
   id :number;
   FirstName :String ;
   LastName:String ;
   email :String;

}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }
}
