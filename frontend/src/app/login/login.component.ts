import { Component } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  formdata = {
    username: '',
    password: ''
  };
  loading = false;
  submit = false;
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {
    this.submit = true;
    this.loading = true;
    this.errorMessage = '';

    const { username, password } = this.formdata;

    this.authService.login(username, password).subscribe({
      next: (response) => {
        this.loading = false;
        localStorage.setItem('token', response.token);
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        this.loading = false;
        this.errorMessage = 'Login failed. Check your credentials.';
      }
    });
  }
}
