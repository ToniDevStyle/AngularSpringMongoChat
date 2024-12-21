import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';  // Importar FormsModule
import { Router, RouterModule } from '@angular/router';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  standalone: true,  // Marcar el componente como standalone
  imports: [FormsModule, RouterModule],  // Importar FormsModule directamente en el componente
})
export class RegisterComponent {
  username: string = '';
  password: string = '';
  confirmPassword: string = '';

  constructor(private router: Router) {}

  register() {
    if (this.password === this.confirmPassword) {
      // Aquí va la lógica de registro
      console.log('Registered with:', this.username, this.password);
    } else {
      console.error('Passwords do not match');
    }
  }

  navigateToLogin() {
    this.router.navigate(['/login']);  // Navega a la página de login
  }
}
