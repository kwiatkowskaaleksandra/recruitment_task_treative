import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Simulation} from "../models/simulation.model";

const baseUrl = 'http://localhost:8080/simulation'

@Injectable({
  providedIn: 'root'
})
export class SimulationService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Simulation[]> {
    return this.http.get<Simulation[]>(`${baseUrl}/getAll`)
  }

  addNewSimulation(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/addNew`, data)
  }

  deleteSimulationById(idSimulation: any): Observable<any> {
    return this.http.delete(`${baseUrl}/deleteSimulationById/${idSimulation}`)
  }

  getAllSimulationResults(idSimulation: any): Observable<any> {
    return this.http.get(`${baseUrl}/getAllSimulationResults/${idSimulation}`)
  }

  updateSimulation(idSimulation: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/updateSimulation/${idSimulation}`, data)
  }

}
