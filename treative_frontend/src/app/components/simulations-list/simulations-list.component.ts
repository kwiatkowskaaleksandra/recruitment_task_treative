import {Component, OnInit} from '@angular/core';
import {Simulation} from "../../models/simulation.model";
import {SimulationService} from "../../services/simulation.service";
import {MatDialog} from "@angular/material/dialog";
import {AddSimulationComponent} from "../add-simulation/add-simulation.component";
import {DeleteConfirmationDialogComponent} from "../delete-confirmation-dialog/delete-confirmation-dialog.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-simulations-list',
  templateUrl: './simulations-list.component.html',
  styleUrls: ['./simulations-list.component.css']
})
export class SimulationsListComponent implements OnInit {
  simulations?: Simulation[];
  paginatedResults: any[] = [];
  currentPage = 1;
  itemsPerPage = 10;

  constructor(private simulationService: SimulationService, public dialog: MatDialog, private router: Router) {}

  ngOnInit(): void {
    this.getAllSimulations()
  }

  getAllSimulations(): void {
    this.simulationService.getAll().subscribe({
      next:(data) => {
        this.simulations = data
        this.onPageChange(this.currentPage);
      },
      error: (e) => console.error(e)
    })
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    const startIndex = (page - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    if (this.simulations) {
      this.paginatedResults = this.simulations.slice(startIndex, endIndex);
    }
  }

  addSimulation(): void {
    const dialogRef = this.dialog.open(AddSimulationComponent, {
      width: '700px'
    })
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'saved') {
        this.getAllSimulations();
      }
    });
  }

  editSimulation(idSimulation: any) {
    this.router.navigate(['/simulation-details', idSimulation]);
  }

  deleteSimulation(idSimulation: any) {
    const dialogRef = this.dialog.open(DeleteConfirmationDialogComponent, {
      width: '400px',
      data: {
        idSimulation: idSimulation,
        simulationName: this.simulations?.find(sim => sim.idSimulation === idSimulation)?.simulationName,
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getAllSimulations();
      }
    });
  }
}
