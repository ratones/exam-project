import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import * as SockJS from 'sockjs-client';
import * as Stomp from '@stomp/stompjs';

export interface ServiceOrder{
  id?:string,
  orderDate?:Date,
  category?:string,
  status?:string,
  dateCompleted?:Date,
  notes:string
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

  public messageBroker:BehaviorSubject<Date> = new BehaviorSubject<Date>(new Date())

  constructor(private http:HttpClient) {
    const socket = new SockJS('http://localhost:8087/gkz-stomp-endpoint');
    let stompClient =  Stomp.Stomp.over(socket);
    stompClient.connect({}, () => {
      console.log("WS connected");
      stompClient.subscribe("/topic/srvorder", (msg:any) => {
        console.log("message received " + msg);
        this.messageBroker.next(new Date())
      })
    })
  }

  getOrders(serviceType:string):Observable<ServiceOrder[]>{
    return this.http.get<ServiceOrder[]>(`${BASE_URL}/orders?serviceType=${serviceType}`)
  }

  getOrder(id:string){
    return this.http.get<ServiceOrder>(`${BASE_URL}/orders/${id}`)
  }

  updateOrder(id:string, order:ServiceOrder){
    return this.http.put<ServiceOrder>(`${BASE_URL}/orders/${id}`, order);
  }

  getSocket(){
    const socket = new SockJS('http://localhost:8087/gkz-stomp-endpoint');
    let st =  Stomp.Stomp.over(socket);
    return st;
  }
}
