import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from "../services/auth/auth.service";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [
    FormsModule
  ],
  standalone: true
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  login() {
    this.authService.login(this.username, this.password).subscribe((response: any) => {
      debugger
      if (response && response.status == "00") {
        this.router.navigate(['/reply']);
      } else {
        this.router.navigate(['/feedback'], { queryParams: { userId: response.userId } });
      }
    }, (error) => {
      console.error('Login error:', error);
    });
  }
}
