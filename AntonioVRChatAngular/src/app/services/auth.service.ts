import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private API_URL = 'http://localhost:8080/auth'; // Adjust according to your backend

  constructor(private http: HttpClient) {}

  // Helper method to get authentication headers with the token
  private getAuthHeaders() {
    const token = localStorage.getItem('token');
    return token ? new HttpHeaders({ Authorization: `Bearer ${token}` }) : new HttpHeaders();
  }

  // Login method
  login(credentials: { username: string; password: string }): Observable<any> {
    return this.http.post(`${this.API_URL}/login`, credentials, {
      responseType: 'text' // Ensure the response is treated as plain text (JWT token)
    });
  }

  // Register method
  register(data: { username: string; password: string }): Observable<any> {
    return this.http.post(`${this.API_URL}/register`, data);
  }

  // Check if user is authenticated (based on presence of token in localStorage)
  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  // Save JWT token in local storage
  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  // Logout method (remove token from localStorage)
  logout(): void {
    localStorage.removeItem('token');
  }

  // Method to make requests with authentication token in headers
  getUserData(): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.get(`${this.API_URL}/user`, { headers });
  }
}
