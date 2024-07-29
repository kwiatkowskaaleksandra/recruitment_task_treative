import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {SimulationsListComponent} from "./components/simulations-list/simulations-list.component";
import {HttpClientModule} from "@angular/common/http";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {AppRoutingModules} from "./app-routing.modules";
import {AddSimulationComponent} from "./components/add-simulation/add-simulation.component";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {MatDialogModule} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import {
  DeleteConfirmationDialogComponent
} from "./components/delete-confirmation-dialog/delete-confirmation-dialog.component";
import {SimulationDetailsComponent} from "./components/simulation-details/simulation-details.component";
import {BaseChartDirective} from "ng2-charts";
import {NgxPaginationModule} from "ngx-pagination";
import {NgbPagination} from "@ng-bootstrap/ng-bootstrap";
import {EditSimulationComponent} from "./components/edit-simulation/edit-simulation.component";

@NgModule({
  declarations: [
    AppComponent,
    SimulationsListComponent,
    AddSimulationComponent,
    DeleteConfirmationDialogComponent,
    SimulationDetailsComponent,
    EditSimulationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModules,
    FormsModule,
    HttpClientModule,
    MatDialogModule,
    MatButtonModule,
    BaseChartDirective,
    NgxPaginationModule,
    NgbPagination
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModules{}
