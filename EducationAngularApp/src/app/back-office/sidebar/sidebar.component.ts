import { Component } from '@angular/core';
import {TokenStorageService} from "../../service/token-storage.service";


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  constructor(private tokenStorage:TokenStorageService) {
  }

  logout() {
    this.tokenStorage.signOut();
    window.location.reload();
  }
}
