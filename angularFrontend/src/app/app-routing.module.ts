import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { NeedAuthGuard } from './auth.guard';
import { LoginComponent } from './components/login/login.component';
import { MaincontainerComponent } from './components/maincontainer/maincontainer.component';

const routes: Routes = [
  {
    path: '',
    component: MaincontainerComponent,
    canActivate: [NeedAuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
