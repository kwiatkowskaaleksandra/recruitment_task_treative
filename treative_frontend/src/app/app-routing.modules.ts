import { RouterModule, Routes } from '@angular/router';
import {NgModule} from "@angular/core";
import {SimulationsListComponent} from "./components/simulations-list/simulations-list.component";
import {SimulationDetailsComponent} from "./components/simulation-details/simulation-details.component";

const routes: Routes = [
  {path: '', redirectTo: 'simulations', pathMatch: 'full'},
  {path: 'simulations', component: SimulationsListComponent},
  {path: 'simulation-details/:id', component: SimulationDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModules {}
