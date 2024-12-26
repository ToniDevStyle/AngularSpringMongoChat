import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';  // Si quieres redirigir al usuario después de un login exitoso
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  // Método que debe coincidir con el template
  login(): void {
    const credentials = {
      username: this.username,
      password: this.password
    };

    this.authService.login(credentials).subscribe({
      next: (token) => {
        console.log('Login exitoso', token);
        // Guarda el token en el almacenamiento local
        this.authService.saveToken(token);
        // Redirigir al usuario después de login exitoso (si lo deseas)
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        console.error('Error al hacer login', err);
        this.errorMessage = 'Credenciales inválidas, por favor intenta de nuevo.';
      }
    });
  }
}
