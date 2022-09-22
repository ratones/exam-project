import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface ServiceOrder{
  id?:string,
  orderDate?:Date,
  category?:string,
  status?:string,
  dateCompleted?:Date,
  notes?:string
  vehicleId?:number,
  vehicleVin?:string,
  orderNo?:number
  materials?:PartsDetail[]
}

export interface PartsDetail{
  id?:string,
  category:"Body" | "Electrics" | "Mechanics" | undefined,
  name:string,
  details:string,
  vehicleVin?:string
}

const BASE_URL = 'http://localhost:8087'

@Injectable({
  providedIn: 'root'
})
export class RepairService {

  constructor(private http:HttpClient) { }

  getOrders(serviceType:string):Observable<ServiceOrder[]>{
    return this.http.get<ServiceOrder[]>(`${BASE_URL}/orders?serviceType=${serviceType}`)
  }

  getOrder(id:string){
    return this.http.get<ServiceOrder>(`${BASE_URL}/orders/${id}`)
  }

  updateOrder(id:string, order:ServiceOrder){
    return this.http.put<ServiceOrder>(`${BASE_URL}/orders/${id}`, order);
  }
}
