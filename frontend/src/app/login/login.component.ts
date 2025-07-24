import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  formdata = {
    username: '',
    password: ''
  };

  submit: boolean = false;
  loading: boolean = false;
  errorMessage: string = '';

  constructor(
    private http: HttpClient,
    private router: Router,
    private authService: AuthService
  ) {}

  onSubmit(): void {
    this.submit = true;
    this.errorMessage = '';
    this.loading = true;

    const loginData = {
      username: this.formdata.username,
      password: this.formdata.password
    };

    this.http
      .post<{ accessToken: string; tokenType: string }>('http://localhost:8080/api/auth/login', loginData)
      .subscribe({
        next: (response) => {
          this.authService.storeAuthData(response);
          this.router.navigate(['/dashboard']);
        },
        error: (error) => {
          this.errorMessage = 'Invalid username or password';
          console.error('Login error', error);
        },
        complete: () => {
          this.loading = false;
        }
      });
  }
}
