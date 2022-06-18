import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { SignupComponent } from './signup/signup.component';
import { AdComponent } from './ad/ad.component';
import { ModeratorPanelComponent } from './moderator_panel/moderator_panel.component';
import { AddAdComponent } from './add_ad/add_ad.component';
import { LoginComponent } from './login/login.component';

const routes: Routes =[
    { path: 'home',             component: HomeComponent },
    { path: 'user-profile',     component: ProfileComponent },
    { path: 'register',           component: SignupComponent },
    { path: 'ogloszenie',          component: AdComponent },
    { path: 'add',          component: AddAdComponent },
    { path: 'moderator-panel',          component: ModeratorPanelComponent },
    { path: 'login',          component: LoginComponent },
    { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes,{
      useHash: true
    })
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
