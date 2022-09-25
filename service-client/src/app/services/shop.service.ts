import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from '@stomp/stompjs';

const BASE_URL = "http://localhost:8088"

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  public messageBroker:BehaviorSubject<Date> = new BehaviorSubject<Date>(new Date())

  constructor(private http:HttpClient) {
    const socket = new SockJS('http://localhost:8088/gkz-stomp-endpoint');
    let stompClient =  Stomp.Stomp.over(socket);
    stompClient.connect({}, () => {
      console.log("WS connected");
      stompClient.subscribe("/topic/shoporder", (msg:any) => {
        console.log("message received " + msg);
        this.messageBroker.next(new Date())
      })
    })
  }

  getOrders(){
    return this.http.get(`${BASE_URL}/orders`)
  }

  updateOrder(id:string, order:any){
    return this.http.put(`${BASE_URL}/orders/${id}`, order);
  }
}
