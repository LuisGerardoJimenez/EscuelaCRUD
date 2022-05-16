import { Component, OnInit } from '@angular/core';
import { Calificacion } from '../modelos/calificacion';
import { CalificacionesService } from '../servicios/calificaciones-service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-calificaciones',
  templateUrl: './calificaciones.component.html',
  styleUrls: ['./calificaciones.component.css']
})
export class CalificacionesComponent implements OnInit {

  listCalificaciones: Calificacion[] = [];
  idAlumno: number;
  nombreAlumno: string;
  promedio: string;
  mostrarMensaje: boolean;
  msg: string;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private calificacionService: CalificacionesService
  ) {

   }

  ngOnInit(): void {
    this.consultarCalificaciones();
    let nom = localStorage.getItem("alumnoNombre")
    this.nombreAlumno = nom!;
    console.log(this.nombreAlumno);
    console.log("Mensaje -> "+localStorage.getItem("msg"));
    if(localStorage.getItem("msg")!){
      this.mostrarMensaje = true;
      this.msg = localStorage.getItem("msg")!;
      localStorage.removeItem("msg");
    } else {
      this.mostrarMensaje = false;
    }
  }

  consultarCalificaciones() {
    let stringIdAlumno = JSON.parse(localStorage.getItem("idAlumno")!);
    this.idAlumno = parseInt(stringIdAlumno);
    this.calificacionService.consultaCalificaciones(this.idAlumno)
      .toPromise()
      .then(
        (data) => {
          this.listCalificaciones = data.calificacionDTOList;
          this.promedio = data.promedio
          console.log(this.listCalificaciones);
        },
        (error) => {
          console.log(error[0]);
        }
      );
  }

  clickRegresar(){
    localStorage.removeItem("idAlumno");
    localStorage.removeItem("alumnoNombre");
    this.router.navigate(['/alumnos']);
  }

  irRegistrarCalificacion(){
    this.router.navigate(['/registro-calificacion']);
  }

  irEditarCalificacion(calificacion:Calificacion){
    localStorage.setItem("idCalificacion", calificacion.idCalificacion.toString());
    this.router.navigate(['/edicion-calificacion']);
  }

  eliminaCalificaciones(calificacion:Calificacion) {
    this.calificacionService.eliminaCalificaciones(calificacion.idCalificacion)
      .toPromise()
      .then(
        (data) => {
          console.log(data);
          this.msg =  data.msg;
          this.mostrarMensaje = true;
          this.consultarCalificaciones();
        },
        (error) => {
          console.log(error[0]);
        }
      );
  }

}
