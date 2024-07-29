import {Component} from "@angular/core";
import {Simulation} from "../../models/simulation.model";
import {SimulationService} from "../../services/simulation.service";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-add-simulation',
  templateUrl: './add-simulation.component.html',
  styleUrls: ['./add-simulation.component.css']
})
export class AddSimulationComponent {
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
  constructor(private simulationService: SimulationService, public dialogRef: MatDialogRef<AddSimulationComponent>) {}

  saveSimulation(): void {
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

    this.simulationService.addNewSimulation(data).subscribe({
      next: () => {
        this.clear()
        this.dialogRef.close('saved');
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
