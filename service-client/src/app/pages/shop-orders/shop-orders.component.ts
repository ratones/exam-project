import { ShopService } from './../../services/shop.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shop-orders',
  templateUrl: './shop-orders.component.html',
  styleUrls: ['./shop-orders.component.scss']
})
export class ShopOrdersComponent implements OnInit {

  orders:any[] = []
  stompClient: any;

  constructor(private service:ShopService) {
    this.stompClient = service.getSocket();
    this.stompClient.connect({}, () => {
      console.log("WS connected");
      this.stompClient.subscribe("/topic/shoporder", (msg:any) => {
        console.log("message received " + msg);
        this.refreshData();
      })
    })
    this.refreshData()
  }

  ngOnInit(): void {
  }

  refreshData(){
    this.service.getOrders().subscribe((data:any) => {
      this.orders = data
    })
  }

}
