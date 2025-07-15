import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login.component';
import { RegisterComponent } from './auth/register.component';
import { GameListComponent } from './games/game-list.component';
import { GameFormComponent } from './games/game-form.component';
import { FilterComponent } from './filters/filter.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: '', redirectTo: 'games', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'games', component: GameListComponent },
  { path: 'filters', component: FilterComponent },
  { path: 'games/add', component: GameFormComponent, canActivate: [AuthGuard] },
  { path: 'games/edit/:id', component: GameFormComponent, canActivate: [AuthGuard] }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
