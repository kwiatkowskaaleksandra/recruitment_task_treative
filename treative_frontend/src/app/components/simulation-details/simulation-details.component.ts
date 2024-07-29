import {Component, OnInit} from "@angular/core";
import {SimulationService} from "../../services/simulation.service";
import {ActivatedRoute} from "@angular/router";
import {ChartOptions, ChartType, ChartData} from 'chart.js';
import {MatDialog} from "@angular/material/dialog";
import {EditSimulationComponent} from "../edit-simulation/edit-simulation.component";

@Component({
  selector: 'app-simulation-details',
  templateUrl: './simulation-details.component.html',
  styleUrls: ['./simulation-details.component.css']
})
export class SimulationDetailsComponent implements OnInit {
  simulation: any;
  dailyResults: any[] = [];
  paginatedResults: any[] = [];
  lineChartData: ChartData<'line'> = {
    datasets: []
  };
  lineChartLabels: string[] = [];
  lineChartOptions: ChartOptions = {
    responsive: true,
  };
  lineChartLegend = true;
  lineChartType: ChartType = 'line';
  currentPage = 1;
  itemsPerPage = 10;
  showInfected = true;
  showHealthy = true;
  showDied = true;
  showRecovered = true;

  constructor(
    private route: ActivatedRoute,
    private simulationService: SimulationService,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.getSimulationResults(id)
  }

  getSimulationResults(idSimulation: any): void {
    this.simulationService.getAllSimulationResults(Number(idSimulation)).subscribe(data => {
      this.simulation = data.simulation;
      this.dailyResults = data.dailyResults;
      this.onPageChange(this.currentPage);
      this.prepareChartData();
    });
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    const startIndex = (page - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.paginatedResults = this.dailyResults.slice(startIndex, endIndex);
  }

  editSimulation(simulation: any): void {
    const dialogRef = this.dialog.open(EditSimulationComponent, {
      width: '700px',
      data: {
        simulation: simulation
      }
    })
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'updated') {
        console.log('Dialog result:', result);
        this.getSimulationResults(simulation.idSimulation);
      }
    });
  }

  prepareChartData(): void {
    const infected: number[] = [];
    const healthy: number[] = [];
    const died: number[] = [];
    const recovered: number[] = [];
    const dates: string[] = [];

    this.dailyResults.forEach(result => {
      infected.push(result.numberOfInfectedPeople);
      healthy.push(result.numberOfHealthyPeople);
      died.push(result.numberOfPeopleWhoDied);
      recovered.push(result.numberOfRecoveredPeople);
      dates.push(result.date);
    });

    const datasets = [];
    if (this.showInfected) {
      datasets.push({ data: infected, label: 'Zakażeni', borderColor: 'rgba(255,0,0,0.3)', backgroundColor: 'rgba(255,0,0,0.3)', fill: false });
    }
    if (this.showHealthy) {
      datasets.push({ data: healthy, label: 'Zdrowi', borderColor: 'rgba(0,255,0,0.3)', backgroundColor: 'rgba(0,255,0,0.3)', fill: false });
    }
    if (this.showDied) {
      datasets.push({ data: died, label: 'Zmarli', borderColor: 'rgba(0,0,0,0.3)', backgroundColor: 'rgba(0,0,0,0.3)', fill: false });
    }
    if (this.showRecovered) {
      datasets.push({ data: recovered, label: 'Wyzdrowiali', borderColor: 'rgba(0,0,255,0.3)', backgroundColor: 'rgba(0,0,255,0.3)', fill: false });
    }

    this.lineChartData = {
      labels: dates,
      datasets: datasets
    };

    this.lineChartLabels = dates;
  }
}
