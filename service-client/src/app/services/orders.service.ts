import { Vehicle } from './vehicle.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface VehicleOrder{
  id:number,
  orderDate:Date,
  category:string,
  status:string,
  dateCompleted:Date,
  notes:string
  vehicle:Vehicle
}

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http:HttpClient) { }

  getVehicleOrders(vehicleId:number):Observable<VehicleOrder[]>{
    return this.http.get<VehicleOrder[]>(`orders/byVehicleId/${vehicleId}`)
  }

  addVehicleOrder(order:VehicleOrder){
    return this.http.post<VehicleOrder>("orders", order);
  }

  updateVehicleOrder(id:number,order:VehicleOrder){
    return this.http.put<VehicleOrder>(`orders/${id}`, order);
  }

  deleteVehicleOrder(id:number){
    return this.http.delete<VehicleOrder>(`orders/${id}`);
  }

}
