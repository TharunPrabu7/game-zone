import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  games: any[] = [];
  pageNo: number = 0;
  pageSize: number = 10;

  constructor(private http: HttpClient, private authService: AuthService) {}

  ngOnInit(): void {
    this.fetchGames();
  }

  fetchGames(): void {
    const authData = this.authService.getAuthData();
    if (!authData) {
      console.error('User not authenticated');
      return;
    }

    const headers = new HttpHeaders({
      'Authorization': `${authData.tokenType} ${authData.accessToken}`
    });

    this.http.get<any[]>(`http://localhost:8080/api/game?pageNo=${this.pageNo}&pageSize=${this.pageSize}`, { headers })
      .subscribe({
        next: (data) => {
          this.games = data;
        },
        error: (err) => {
          console.error('Error fetching games', err);
        }
      });
  }

  nextPage(): void {
    this.pageNo++;
    this.fetchGames();
  }

  prevPage(): void {
    if (this.pageNo > 0) {
      this.pageNo--;
      this.fetchGames();
    }
  }
}
