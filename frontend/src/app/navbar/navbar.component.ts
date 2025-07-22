import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn: boolean = false;

  constructor(public auth: AuthService) {}

  ngOnInit(): void {
    // Subscribe to login state
    this.auth.isLoggedIn().subscribe((status) => {
      this.isLoggedIn = status;
    });
  }

  logout(): void {
    this.auth.removeToken(); // This also handles navigation to /login
  }
}
