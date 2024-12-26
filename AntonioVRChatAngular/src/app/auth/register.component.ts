import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../services/auth.service'; // Importa AuthService

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule],
})
export class RegisterComponent {
  username: string = '';
  password: string = '';
  confirmPassword: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register() {
    if (!this.username || !this.password || !this.confirmPassword) {
      this.errorMessage = 'All fields are required';
      return;
    }

    if (this.password !== this.confirmPassword) {
      this.errorMessage = 'Passwords do not match';
      return;
    }

    this.authService
      .register({ username: this.username, password: this.password })
      .subscribe(
        (response) => {
          this.authService.saveToken(response.token); // Guarda el token
          this.router.navigate(['/chat']); // Redirige al chat
        },
        (error) => {
          this.errorMessage = 'Registration failed. Please try again.';
          console.error(error);
        }
      );
  }

  navigateToLogin() {
    this.router.navigate(['/login']);
  }
}
