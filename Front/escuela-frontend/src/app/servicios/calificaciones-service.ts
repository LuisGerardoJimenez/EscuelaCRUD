import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { endpoints } from '../../environments/environment';
import { RegistroCalificacion } from '../modelos/registro-calificacion';
import { RegistroCalificacionResponse } from '../modelos/registro-calificacion-response';
import { EdicionCalificacion } from '../modelos/edicion-calificacion';

@Injectable({
    providedIn: 'root',
  })
export class CalificacionesService {

    constructor(private http: HttpClient) {}

    consultaCalificacionPorId(idCalificacion:number): Observable<any> {
      return this.http.get<any>(endpoints.endpoint + `calificacion/consulta?calificacion=${idCalificacion}`).pipe(
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

    consultaCalificaciones(idAlumno:number): Observable<any> {
        return this.http.get<any>(endpoints.endpoint + `calificacion/consultar?alumno=${idAlumno}`).pipe(
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

    registrarCalificaciones(calificacion:RegistroCalificacion): Observable<RegistroCalificacionResponse> {
        return this.http.post<any>(endpoints.endpoint + `calificacion/registrar`, calificacion).pipe(
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

    editarCalificaciones(calificacion:EdicionCalificacion): Observable<any> {
        return this.http.put<any>(endpoints.endpoint + `calificacion/actualizar`, calificacion).pipe(
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

    eliminaCalificaciones(idCalificacion:number): Observable<any> {
        return this.http.delete<any>(endpoints.endpoint + `calificacion/eliminar?calificacion=${idCalificacion}`).pipe(
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
