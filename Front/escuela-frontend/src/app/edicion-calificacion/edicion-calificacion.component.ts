import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { EdicionCalificacion } from '../modelos/edicion-calificacion';
import { Materia } from '../modelos/materia';
import { RegistroCalificacionResponse } from '../modelos/registro-calificacion-response';
import { CalificacionesService } from '../servicios/calificaciones-service';
import { MateriasService } from '../servicios/materias-service';

@Component({
  selector: 'app-edicion-calificacion',
  templateUrl: './edicion-calificacion.component.html',
  styleUrls: ['./edicion-calificacion.component.css']
})
export class EdicionCalificacionComponent implements OnInit {

  calificacionForm: FormGroup;
  calificacion = new EdicionCalificacion();
  calificacionResponse = new RegistroCalificacionResponse();
  submitted = false;
  listMaterias: Materia[] = [];
  idCalificacion: number;
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
    this.consultarCalificacion();
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

  consultarCalificacion() {
    let stringIdCalificacion = JSON.parse(localStorage.getItem("idCalificacion")!);
    this.idCalificacion = parseInt(stringIdCalificacion);
    this.calificacionService.consultaCalificacionPorId(this.idCalificacion)
      .toPromise()
      .then(
        (data) => {
          this.f['idMateria'].setValue(data.idMateria)
          this.f['calificacion'].setValue(data.calificacion)
          console.log(data);
        },
        (error) => {
          console.log(error[0]);
        }
      );
  }

  get f() { return this.calificacionForm.controls; }

  editarCalificacion() {
    this.submitted = true;
    if (this.calificacionForm.invalid) {
      return;
    }

    let stringIdAlumno = JSON.parse(localStorage.getItem("idAlumno")!);
    let idAlumno = parseInt(stringIdAlumno);
    this.calificacion.idCalificacion = this.idCalificacion;
    this.calificacion.idAlumno = idAlumno;
    this.calificacion.idMateria  = this.f['idMateria'].value;
    this.calificacion.calificacion = this.f['calificacion'].value;
    this.calificacionService.editarCalificaciones(this.calificacion)
      .toPromise()
      .then(
        (data) => {
          this.calificacionResponse = data!;
          console.log(this.calificacionResponse);
          localStorage.setItem("success", this.calificacionResponse.success);
          localStorage.setItem("msg", this.calificacionResponse.msg);
          localStorage.removeItem("idCalificacion");
          this.router.navigate(['/calificaciones']);
        },
        (error) => {
          console.log(error[0]);
        }
      );
  }

  clickRegresar(){
    localStorage.removeItem("idCalificacion");
    this.router.navigate(['/calificaciones']);
  }

}
