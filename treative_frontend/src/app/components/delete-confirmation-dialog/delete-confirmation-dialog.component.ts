import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {SimulationService} from "../../services/simulation.service";

@Component({
  selector: 'app-delete-confirmation-dialog',
  templateUrl: './delete-confirmation-dialog.component.html',
  styleUrl: './delete-confirmation-dialog.component.css'
})
export class DeleteConfirmationDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<DeleteConfirmationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { idSimulation: number, simulationName: string },
    private simulationService: SimulationService
  ) {}

  onNoClick(): void {
    this.dialogRef.close(false);
  }

  onYesClick(): void {
    this.simulationService.deleteSimulationById(this.data.idSimulation).subscribe({
      next:() => {
        console.log('Deleted simulation with id', this.data.idSimulation);
        this.dialogRef.close(true);
      }, error: (e) => console.log(e)
    })
  }
}
