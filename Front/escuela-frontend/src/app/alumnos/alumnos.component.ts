import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Alumno } from '../modelos/alumno';
import { AlumnosService } from '../servicios/alumnos-service';

@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  styleUrls: ['./alumnos.component.css']
})
export class AlumnosComponent implements OnInit {

  listAlumnos: Alumno[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private alumnosService: AlumnosService
  ) { }

  ngOnInit(): void {
    this.consultarAlumnos();
  }

  private async consultarAlumnos() {
    await this.alumnosService.consultarAlumnos()
      .toPromise()
      .then(
        (data) => {
          this.listAlumnos = data.alumnosDTOList;
          console.log(this.listAlumnos);
        },
        (error) => {
          console.log(error[0]);
        }
      );
  }

}
