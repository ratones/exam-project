import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from '@stomp/stompjs';

const BASE_URL = "http://localhost:8088"

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(private http:HttpClient) { }

  getOrders(){
    return this.http.get(`${BASE_URL}/orders`)
  }

  getSocket(){
    const socket = new SockJS('http://localhost:8088/gkz-stomp-endpoint');
    return Stomp.Stomp.over(socket);
  }
}
