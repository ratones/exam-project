import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Vehicle } from './vehicle.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import * as SockJS from 'sockjs-client';
import * as Stomp from '@stomp/stompjs';

export interface VehicleOrder{
  id:number,
  orderDate:Date,
  category:string,
  status:string,
  dateCompleted:Date,
  notes:string
  vehicle:Vehicle,
  isSent:boolean
}

export interface Deficiency{
  id?:number,
  description:string,
  order:VehicleOrder|null
}

const BASE_URL = 'http://localhost:8081'

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  public messageBroker:BehaviorSubject<Date> = new BehaviorSubject<Date>(new Date())

  constructor(private http:HttpClient) {
    const socket = new SockJS('http://localhost:8081/gkz-stomp-endpoint');
    let stompClient = Stomp.Stomp.over(socket);
    stompClient.connect({}, () => {
      console.log("WS connected");
      stompClient.subscribe("/topic/msg", (msg:any) => {
        console.log("message received " + msg);
        this.messageBroker.next(new Date())
      })
    })
  }

  getVehicleOrders(vehicleId:number):Observable<VehicleOrder[]>{
    return this.http.get<VehicleOrder[]>(`${BASE_URL}/orders/byVehicleId/${vehicleId}`)
  }

  addVehicleOrder(order:VehicleOrder){
    return this.http.post<VehicleOrder>(BASE_URL + "/orders", order);
  }

  updateVehicleOrder(id:number,order:VehicleOrder){
    return this.http.put<VehicleOrder>(`${BASE_URL}/orders/${id}`, order);
  }

  sendVehicleOrder(id:number,order:VehicleOrder){
    return this.http.post<VehicleOrder>(`${BASE_URL}/orders/send/${id}`, order);
  }

  deleteVehicleOrder(id:number){
    return this.http.delete<VehicleOrder>(`${BASE_URL}/orders/${id}`);
  }

  getOrderDeficiencies(orderId:number):Observable<Deficiency[]>{
    return this.http.get<Deficiency[]>(`${BASE_URL}/orders/deficiencies/${orderId}`)
  }

}
