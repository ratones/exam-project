import { RepairService } from './../../services/repair.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceOrder } from 'src/app/services/repair.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.scss']
})
export class OrderListComponent implements OnInit {

  serviceType!:string

  orders:ServiceOrder[] = []

  constructor(private activeRoute:ActivatedRoute, private service:RepairService, private router:Router) {
    this.viewDetails = this.viewDetails.bind(this);
  }

  ngOnInit(): void {
    this.activeRoute.params.subscribe(p => {
      this.serviceType = p.type
      this.refreshData()
    })
  }

  refreshData(){
    this.service.getOrders(this.serviceType).subscribe(data => {
      this.orders = data;
    })
  }

  viewDetails(ev:any){
    console.log(ev);
    this.router.navigateByUrl(`order/${ev.row.data.id}`)
  }

}
