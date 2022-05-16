import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AlumnosComponent } from './alumnos/alumnos.component';
import { CalificacionesComponent } from './calificaciones/calificaciones.component';
import { RegistroCalificacionComponent } from './registro-calificacion/registro-calificacion.component';
import { EdicionCalificacionComponent } from './edicion-calificacion/edicion-calificacion.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent},
  { path: 'alumnos', component: AlumnosComponent},
  { path: 'calificaciones', component: CalificacionesComponent},
  { path: 'registro-calificacion', component: RegistroCalificacionComponent},
  { path: 'edicion-calificacion', component: EdicionCalificacionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
