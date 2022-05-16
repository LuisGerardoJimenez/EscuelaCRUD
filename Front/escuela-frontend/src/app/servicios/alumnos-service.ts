import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { endpoints } from '../../environments/environment';

@Injectable({
    providedIn: 'root',
  })
export class AlumnosService {

    constructor(private http: HttpClient) {}

    consultarAlumnos(): Observable<any> {
        return this.http.get<any>(endpoints.endpoint + `alumnos/consultar`).pipe(
          tap(
            (response) => {
              console.log(response);
            },
            (error) => {
                console.log(error);
            }
          )
        );
      }
}
