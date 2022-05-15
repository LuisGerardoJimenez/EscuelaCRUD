import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../servicios/login-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private loginService: LoginService
  ) { }

  ngOnInit() {

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

  }

  get f() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;

    // Validación de formulario
    if (this.loginForm.invalid) {
      console.log("Todos los campos son obligatorios");
      return;
    }
    console.log("Login");
    this.loginService.login(this.f['username'].value, this.f['password'].value).subscribe(data => {
      this.router.navigate(['/alumnos']);
    },
      error => {
        console.log(error);
        console.log(error.status);
        if(error.status===401){
          console.log('Usuario o contraseña incorrectos');
          this.router.navigate(['/login']);
  }
      }
    );
    //this.router.navigate(['/alumnos']);
  }

}
