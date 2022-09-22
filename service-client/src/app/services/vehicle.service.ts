import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Vehicle {
  id:number,
  vin:string,
  make:string,
  model:string,
  year:number,
  owner:string
}

const BASE_URL = 'http://localhost:8081'

@Injectable({
  providedIn: 'root'
})
export class VehicleService {



  constructor(private http:HttpClient) { }

  getVehicles():Observable<Vehicle[]>{
    return this.http.get<Vehicle[]>(BASE_URL + '/vehicles');
  }

  insertVehicle(vehicle:Vehicle):Observable<Vehicle>{
    return this.http.post<Vehicle>(BASE_URL + '/vehicles', vehicle);
  }

  updateVehicle(key:number,vehicle:Vehicle):Observable<Vehicle>{
    return this.http.put<Vehicle>(`${BASE_URL}/vehicles/${key}`, vehicle);
  }

  removeVehicle(key:number):Observable<Vehicle>{
    return this.http.delete<Vehicle>(`${BASE_URL}/vehicles/${key}`);
  }
}
