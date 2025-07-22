import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private registerUrl = 'http://localhost:8080/api/auth/register';
  private loginUrl = 'http://localhost:8080/api/auth/login';

  // Reactive auth state
  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());

  constructor(private http: HttpClient, private router: Router) {}

  // Register User
  register(name: string, email: string, password: string) {
    const body = { name, email, password };
    return this.http.post<any>(this.registerUrl, body);
  }

  // Login User
  login(username: string, password: string) {
    return this.http.post<any>(this.loginUrl, { username, password });
  }

  // Store full auth object
  storeAuthData(data: { accessToken: string, tokenType: string }) {
    localStorage.setItem('authData', JSON.stringify(data));
    this.loggedIn.next(true);
  }

  // Get token
  getToken(): string | null {
    const authData = localStorage.getItem('authData');
    if (authData) {
      return JSON.parse(authData).accessToken;
    }
    return null;
  }

  // Check token presence
  hasToken(): boolean {
    return !!this.getToken();
  }

  // Public observable to subscribe in components (like navbar)
  isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  // Logout
  removeToken() {
    localStorage.removeItem('authData');
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }

  // Auto-redirect if already logged in
  canAuthenticate() {
    if (this.hasToken()) {
      this.router.navigate(['/dashboard']);
    }
  }

  // Redirect if trying to access protected route without login
  canAccess() {
    if (!this.hasToken()) {
      this.router.navigate(['/login']);
    }
  }
}
