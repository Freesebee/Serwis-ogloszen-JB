import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app.routing';

import { AdComponent } from './ad/ad.component';
import { AddAdComponent } from './add_ad/add_ad.component';
import { AppComponent } from './app.component';
import { CategoryComponent } from './category/category.component';
import { EditAdComponent } from './edit_ad/edit_ad.component';
import { ModeratorPanelComponent } from './moderator_panel/moderator_panel.component';
import { ProfileComponent } from './profile/profile.component';
import { FooterComponent } from './shared/footer/footer.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { SignupComponent } from './signup/signup.component';

import { AdDetailsComponent } from './ad/ad-details/ad-details.component';
import { JwtTokenInterceptor } from "./shared/interceptors/jwt.token.interceptor";
import { HomeModule } from './home/home.module';
import { LoginComponent } from './login/login.component';
import { LoadingSpinnerComponent } from './shared/loading-spinner/loading-spinner.component';
import { AuthGuard } from './shared/services/auth-guard.service';
import { AuthService } from './shared/services/auth.service';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    AdComponent,
    ModeratorPanelComponent,
    AddAdComponent,
    ProfileComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    LoadingSpinnerComponent,
    AdDetailsComponent,
    EditAdComponent,
    CategoryComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    RouterModule,
    AppRoutingModule,
    HomeModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtTokenInterceptor,
      multi: true
    },
    AuthGuard, AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
