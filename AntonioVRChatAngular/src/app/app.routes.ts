import { Routes } from '@angular/router';
import { ChatComponent } from './pages/chat/chat.component';

export const appRoutes: Routes = [
  { path: '', component: ChatComponent }, // Página de inicio
  { path: '**', redirectTo: '' }, // Redirige cualquier ruta no válida a la página de inicio
];
