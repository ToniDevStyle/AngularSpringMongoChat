import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';  // Importar FormsModule

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,  // Marcar el componente como standalone
  imports: [FormsModule],  // Importar FormsModule directamente en el componente
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor() {}

  login() {
    // Aquí va la lógica de inicio de sesión
    console.log('Logged in with:', this.username, this.password);
  }
}
