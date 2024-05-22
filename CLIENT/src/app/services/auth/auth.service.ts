import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = 'http://localhost:6565/login';

  constructor(private httpClient: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    const url = `${this.loginUrl}/login`;
    const httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { username, password };
    return this.httpClient.post<any>(url, body, {
      headers: httpHeaders,
      responseType: 'json'
    });
  }
}

