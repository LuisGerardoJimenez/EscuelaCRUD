import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { security, endpoints } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
  })
export class LoginService {

    private authenticationUrl = endpoints.authServer + '/oauth/token';

    private clientId : string;
    private clientSecret : string;

    constructor(
        private router: Router,
        private _http: HttpClient,
        private _router: Router,
    ){
        this.clientId = security.clientId;
        this.clientSecret = security.clienteSecret;
    }


    /**
   * Obtención del Token de Acceso
   */
  public login(user : string, password : string) {
    console.log("Entrando login")
    const body = `grant_type=password&username=${encodeURIComponent(user)}&password=${encodeURIComponent(password)}`;

    return this._http.post(this.authenticationUrl, body, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8')
        .set('Authorization', 'Basic ' + btoa(this.clientId + ':' + this.clientSecret))
    }).pipe(

      tap((data: any) => {
        if (data && data.access_token) {
          let token = data.access_token;
          localStorage.setItem('token', token);
        }
      })
    );
  }

}
