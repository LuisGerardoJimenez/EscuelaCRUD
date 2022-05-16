import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Materia } from '../modelos/materia';
import { RegistroCalificacion } from '../modelos/registro-calificacion';
import { RegistroCalificacionResponse } from '../modelos/registro-calificacion-response';
import { CalificacionesService } from '../servicios/calificaciones-service';
import { MateriasService } from '../servicios/materias-service';

@Component({
  selector: 'app-registro-calificacion',
  templateUrl: './registro-calificacion.component.html',
  styleUrls: ['./registro-calificacion.component.css']
})
export class RegistroCalificacionComponent implements OnInit {

  calificacionForm: FormGroup;
  calificacion = new RegistroCalificacion();
  calificacionResponse = new RegistroCalificacionResponse();
  submitted = false;
  listMaterias: Materia[] = [];
  idAlumno: number;
  nombreAlumno: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private calificacionService: CalificacionesService,
    private materiaService: MateriasService
  ) { }

  ngOnInit(){
    this.calificacionForm = this.formBuilder.group({
      idMateria: new FormControl([], [Validators.required]),
      calificacion: new FormControl(0, 
        [Validators.required, Validators.maxLength(5), 
        Validators.maxLength(10),
        Validators.pattern('^[0-9]{1,2}\\.[0-9]{1,2}$')])
    });
    this.consultarMaterias();
    let nom = localStorage.getItem("alumnoNombre")
    this.nombreAlumno = nom!;
    let idAl = localStorage.getItem("idAlumno")
    this.idAlumno = parseInt(idAl!);
  }

  private async consultarMaterias() {
    await this.materiaService.consultarMaterias()
      .toPromise()
      .then(
        (data) => {
          this.listMaterias = data.materiaDTOList;
          console.log(this.listMaterias);
        },
        (error) => {
          console.log(error[0]);
        }
      );
  }

  get f() { return this.calificacionForm.controls; }

  registraCalificacion() {
    this.submitted = true;
    console.log(this.calificacionForm)
    console.log(this.calificacionForm.invalid)
    console.log()
    console.log()
    if (this.calificacionForm.invalid) {
      return;
    }

    let stringIdAlumno = JSON.parse(localStorage.getItem("idAlumno")!);
    let idAlumno = parseInt(stringIdAlumno);
    this.calificacion.idAlumno = idAlumno;
    this.calificacion.idMateria  = this.f['idMateria'].value;
    this.calificacion.calificacion = this.f['calificacion'].value;
    this.calificacionService.registrarCalificaciones(this.calificacion)
      .toPromise()
      .then(
        (data) => {
          this.calificacionResponse = data!;
          localStorage.setItem("success", this.calificacionResponse.success);
          localStorage.setItem("msg", this.calificacionResponse.msg);
          console.log(this.calificacionResponse);
          this.router.navigate(['/calificaciones']);
        },
        (error) => {
          console.log(error[0]);
        }
      );
  }

  clickRegresar(){
    this.router.navigate(['/calificaciones']);
  }

}
