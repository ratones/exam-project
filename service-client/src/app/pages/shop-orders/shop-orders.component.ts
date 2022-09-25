import { ShopService } from './../../services/shop.service';
import { Component, OnInit } from '@angular/core';
import notify from 'devextreme/ui/notify';

@Component({
  selector: 'app-shop-orders',
  templateUrl: './shop-orders.component.html',
  styleUrls: ['./shop-orders.component.scss']
})
export class ShopOrdersComponent implements OnInit {

  orders:any[] = []
  stompClient: any;

  constructor(private service:ShopService) {
    this.refreshData()
    service.messageBroker.subscribe(() => {
      this.refreshData()
    })
  }

  ngOnInit(): void {
  }

  refreshData(){
    this.service.getOrders().subscribe((data:any) => {
      this.orders = data
    })
  }

  canCompleteOrder(order:any){
    return order.parts.filter((p:any) => !p.available).length > 0
          || order.status == 'partsDelivered'
  }

  saveOrder(ev:any, order:any){
    console.log(ev);
    ev.changes.forEach((element:any) => {
      let crt = order.parts.find((p:any) => {return p.id == element.key})
      crt.available = element.data.available
    });
    this.service.updateOrder(order.id, order).subscribe(() =>{
      notify("Order saved!","success", 5000)
    })
  }

  completeOrder(order:any){
    order.status = 'partsDelivered'
    this.service.updateOrder(order.id, order).subscribe(() =>{
      notify("Order completed","success", 5000)
    })
  }

}
