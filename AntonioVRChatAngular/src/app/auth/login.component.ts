import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';  // Importar FormsModule
import { Router } from '@angular/router';  // Importar Router
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,  // Marcar el componente como standalone
  imports: [FormsModule, RouterModule],  // Importar FormsModule directamente en el componente
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private router: Router) {}  // Inyectar el servicio Router

  login() {
    // Aquí va la lógica de inicio de sesión
    console.log('Logged in with:', this.username, this.password);
    // Redirigir al chat o a la página que corresponda después de iniciar sesión
    this.router.navigate(['/chat']);
  }

  navigateToRegister() {
    // Navegar al componente de registro
    this.router.navigate(['/register']);
  }
}
