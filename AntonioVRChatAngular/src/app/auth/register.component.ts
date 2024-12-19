import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';  // Importar FormsModule

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  standalone: true,  // Marcar el componente como standalone
  imports: [FormsModule],  // Importar FormsModule directamente en el componente
})
export class RegisterComponent {
  username: string = '';
  password: string = '';
  confirmPassword: string = '';

  constructor() {}

  register() {
    if (this.password === this.confirmPassword) {
      // Aquí va la lógica de registro
      console.log('Registered with:', this.username, this.password);
    } else {
      console.error('Passwords do not match');
    }
  }
}
