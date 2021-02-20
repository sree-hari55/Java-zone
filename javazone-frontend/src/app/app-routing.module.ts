import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './auth/register/register.component';
import { RegisterSucessComponent } from './auth/register-sucess/register-sucess.component';
import { LoginComponent } from './auth/login/login.component';
import { HomeComponent } from './home/home.component';
import { AddPostComponent } from './add-post/add-post.component';
import { AuthGuard } from './auth-guard';
import { PostComponent } from './post/post.component';
import { AboutComponent } from './about/about.component';


const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'home', component:HomeComponent},
  {path:'register', component:RegisterComponent},
  {path:'register-sucess', component:RegisterSucessComponent},
  {path:'login', component:LoginComponent},
  {path:'post/:id' , component:PostComponent},
  {path:'add-post', component:AddPostComponent,canActivate: [AuthGuard]},
  {path:'aboutus',component:AboutComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
