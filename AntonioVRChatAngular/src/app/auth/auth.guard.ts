import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service'; // Importa tu servicio de autenticación

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    console.log('AuthGuard triggered. Current route:', this.router.url);

    if (this.authService.isAuthenticated()) {
      return true;
    }

  
    this.router.navigate(['/login']);
    return false;
  }
}
