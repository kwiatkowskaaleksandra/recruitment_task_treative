import {Component, Inject, OnInit} from "@angular/core";
import {Simulation} from "../../models/simulation.model";
import {SimulationService} from "../../services/simulation.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-edit-simulation',
  templateUrl: './edit-simulation.component.html',
  styleUrls: ['./edit-simulation.component.css']
})
export class EditSimulationComponent implements OnInit{
  simulation: Simulation = {
    simulationName: '',
    populationSize: 0,
    initialNumberOfInfectedPeople: 0,
    infectiveRate: 0,
    mortalityRate: 0,
    recoveryDays: 0,
    deathDays: 0,
    simulationDays: 0
  }
  isError = false;
  errorMessage = ''

  constructor(private simulationService: SimulationService,
              public dialogRef: MatDialogRef<EditSimulationComponent>,
              @Inject(MAT_DIALOG_DATA) public data: { simulation: Simulation},) {}

  ngOnInit(): void {
    this.simulationService.getAllSimulationResults(Number(this.data.simulation.idSimulation)).subscribe(data => {
      this.simulation = data.simulation;
    });
  }

  updateSimulation(): void {
    const data = {
      name: this.simulation.simulationName,
      populationSize: this.simulation.populationSize,
      initialNumberOfInfectedPeople: this.simulation.initialNumberOfInfectedPeople,
      infectiveRate: this.simulation.infectiveRate,
      mortalityRate: this.simulation.mortalityRate,
      recoveryDays: this.simulation.recoveryDays,
      deathDays: this.simulation.deathDays,
      simulationDays: this.simulation.simulationDays
    }

    this.simulationService.updateSimulation(this.data.simulation.idSimulation, data).subscribe({
      next: () => {
        this.clear()
        this.dialogRef.close('updated');
      }, error: (e) => {
        console.log(e)
        this.isError = true
        this.errorMessage = e.error.message
      }
    })
  }

  onNoClick(): void {
    this.clear()
    this.dialogRef.close();
  }

  clear(): void {
    this.errorMessage = ''
    this.isError = false
    this.simulation = {
      simulationName: '',
      populationSize: 0,
      initialNumberOfInfectedPeople: 0,
      infectiveRate: 0,
      mortalityRate: 0,
      recoveryDays: 0,
      deathDays: 0,
      simulationDays: 0
    }
  }

}
