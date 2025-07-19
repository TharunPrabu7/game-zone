import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  formdata = {
    username: '',
    email: '',
    password: '',
    role: 'USER'
  };
  loading = false;
  submit = false;
  errorMessage = '';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.submit = true;

    if (!this.formdata.username || !this.formdata.password) {
      return;
    }

    this.loading = true;
    this.http.post('http://localhost:8080/api/auth/register', this.formdata)
      .subscribe({
        next: () => {
          this.router.navigate(['/login']);
        },
        error: (error) => {
          console.error(error);
          this.errorMessage = 'Registration failed. Try again.';
          this.loading = false;
        }
      });
  }
}
