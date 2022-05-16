import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class HttpAuthInterceptor implements HttpInterceptor {

  constructor(private _router: Router) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = localStorage.getItem('token');

    const customReq = req.clone(
      (token != null) ? {setHeaders: { Authorization: `Bearer ${token}`}} : {}
    );

    return next.handle(customReq).pipe(
      tap(event => {
        if (event instanceof HttpResponse) { }
      },
      )
    )
  }

}
