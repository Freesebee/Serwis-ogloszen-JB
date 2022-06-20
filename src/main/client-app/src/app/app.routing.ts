import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AdDetailsComponent } from './ad/ad-details/ad-details.component';
import { AdComponent } from './ad/ad.component';
import { AddAdComponent } from './add_ad/add_ad.component';
import { CategoryComponent } from './category/category.component';
import { EditAdComponent } from './edit_ad/edit_ad.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ModeratorPanelComponent } from './moderator_panel/moderator_panel.component';
import { ProfileComponent } from './profile/profile.component';
import { AuthGuard } from './shared/services/auth-guard.service';
import { AuthService } from './shared/services/auth.service';
import { SignupComponent } from './signup/signup.component';
import { AddCategoryComponent } from './add-category/add-category.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'user-profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'register', component: SignupComponent },
  { path: 'ogloszenia', component: AdComponent },
  { path: 'ogloszenia/:id', component: AdDetailsComponent },
  { path: 'add', component: AddAdComponent, canActivate: [AuthGuard] },
  { path: 'edit/:id', component: EditAdComponent, canActivate: [AuthGuard] },
  { path: 'moderator-panel', component: ModeratorPanelComponent, canActivate: [AuthGuard] },
  { path: 'kategorie', component: CategoryComponent },
  { path: 'add-category', component: AddCategoryComponent },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes, {
      useHash: true
    })
  ],
  exports: [
  ],
  providers: [AuthGuard, AuthService]
})
export class AppRoutingModule { }
