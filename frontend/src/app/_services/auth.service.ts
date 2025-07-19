import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private registerUrl = 'http://localhost:8080/api/auth/register';
  private loginUrl = 'http://localhost:8080/api/auth/login';

  constructor(private http: HttpClient, private router: Router) {}

  // Register User
  register(name: string, email: string, password: string) {
    const body = { name, email, password };
    return this.http.post<any>(this.registerUrl, body);
  }

  // Login User
  login(email: string, password: string) {
    return this.http.post<any>(this.loginUrl, { email, password });
  }

  // Store JWT token
  storeToken(token: string) {
    localStorage.setItem('jwtToken', token);
  }

  // Get token
  getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

  // Check if user is authenticated
  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  // Remove token (logout)
  removeToken() {
    localStorage.removeItem('jwtToken');
  }

  // Redirect if already authenticated
  canAuthenticate() {
    if (this.isAuthenticated()) {
      this.router.navigate(['/dashboard']);
    }
  }

  // Protect access to restricted routes
  canAccess() {
    if (!this.isAuthenticated()) {
      this.router.navigate(['/login']);
    }
  }
}
