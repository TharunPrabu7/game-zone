import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';

interface AuthResponse {
  accessToken: string;
  tokenType: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private registerUrl = 'http://localhost:8080/api/auth/register';
  private loginUrl = 'http://localhost:8080/api/auth/login';

  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());

  constructor(private http: HttpClient, private router: Router) {}

  // Register User
  register(name: string, email: string, password: string): Observable<any> {
    return this.http.post<any>(this.registerUrl, { name, email, password });
  }

  // Login User
  login(username: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.loginUrl, { username, password });
  }

  // Store full auth object
  storeAuthData(data: AuthResponse): void {
    localStorage.setItem('authData', JSON.stringify(data));
    this.loggedIn.next(true);
  }

  // Get token (Bearer ...)
  getToken(): string | null {
    const authData = localStorage.getItem('authData');
    if (authData) {
      const { accessToken, tokenType } = JSON.parse(authData);
      return `${tokenType} ${accessToken}`;
    }
    return null;
  }

  // Get full auth object
  getAuthData(): AuthResponse | null {
    const data = localStorage.getItem('authData');
    return data ? JSON.parse(data) : null;
  }

  // Token presence
  hasToken(): boolean {
    return !!this.getToken();
  }

  // Auth state observable
  isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  // Logout user
  removeToken(): void {
    localStorage.removeItem('authData');
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }

  // Redirect to dashboard if already logged in
  canAuthenticate(): void {
    if (this.hasToken()) {
      this.router.navigate(['/dashboard']);
    }
  }

  // Redirect to login if not logged in
  canAccess(): void {
    if (!this.hasToken()) {
      this.router.navigate(['/login']);
    }
  }
}
