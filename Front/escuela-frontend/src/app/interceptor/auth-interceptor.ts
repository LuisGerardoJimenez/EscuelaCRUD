import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    error: any[] = [];
    constructor() { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const token = localStorage.getItem('token');

        const customReq = request.clone(
            //(request.url){}
            (token != null) ? { setHeaders: { Authorization: `Bearer ${token}` } } : {}
        );

        return next.handle((request.url.includes('oauth')) ? request : customReq).pipe(catchError((err: HttpErrorResponse) => {
            if (err.hasOwnProperty('status')) {
                this.error = [];
                console.log(request)
                console.log(customReq)
                console.log("Contiene? : "+request.url.includes('oauth'))
                switch (err.status) {
                    case 400:
                        this.error.push('Usuario y/o contraseña incorrecto.');
                        break
                    case 401:
                        this.error.push('Usuario y/o contraseña incorrecto.');
                        break
                    case 403:
                        this.error.push('Acceso denegado.');
                        break
                    default:
                        if (err.error != null && (typeof err.error !== 'object')) {
                            this.error.push(err.error);
                        } else if (err.error.userMessage !== undefined) {
                            this.error.push(err.error.userMessage);
                        } else if (typeof err.error === 'object') {
                            this.error.push(err.error.text);
                        } else {
                            switch (err.status) {
                                case 404:
                                    this.error.push('No se encontraron resultados.');
                                    break
                                case 409:
                                    this.error.push('Existe un conflicto con la solicitud.');
                                    break
                                case 500:
                                    this.error.push('Se encontró un error interno en el servidor.');
                                    break;
                            }
                        }
                }
                return throwError(this.error);
            } else {
                return throwError(err);
            }
        }))
    }
}