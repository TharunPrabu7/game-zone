import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  accessToken: string | null = '';
  tokenType: string | null = '';

  ngOnInit(): void {
    const data = localStorage.getItem('authData');
    if (data) {
      const parsed = JSON.parse(data);
      this.accessToken = parsed.accessToken;
      this.tokenType = parsed.tokenType;
    }
  }
}
