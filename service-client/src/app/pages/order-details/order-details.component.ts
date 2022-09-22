import { ActivatedRoute } from '@angular/router';
import { ServiceOrder } from 'src/app/services/repair.service';
import { RepairService } from './../../services/repair.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.scss']
})
export class OrderDetailsComponent implements OnInit {

  order:ServiceOrder = {}

  constructor(private service:RepairService, activeRoute:ActivatedRoute) {
    activeRoute.params.subscribe(p =>{
      service.getOrder(p.id).subscribe((data:any) => {
        this.order = data
      })
    })
  }

  ngOnInit(): void {
  }

  handleVehicleReceived(){
    this.order.status = 'vehicleReceived';
    if(this.order.id){
      this.service.updateOrder(this.order.id, this.order).subscribe((data:any) => {
        this.order = data;
      });
    }
  }

  addMaterials(){
    if(!this.order.materials){
      this.order.materials = [];
    }
    this.order.materials.push({
      vehicleVin:this.order.vehicleVin,
      details:'',
      category:undefined,
      name:''
    })
  }

  deleteMaterials(item:any){
    this.order.materials?.splice(this.order.materials.indexOf(item),1);
  }

  saveMaterialsOrder(){
    if(this.order.id){
      this.service.updateOrder(this.order.id, this.order).subscribe((data:any) => {
        this.order = data;
      });
    }
  }
  sendMaterialsOrder(){
    this.order.status = 'waitingMaterials'
    if(this.order.id){
      this.service.updateOrder(this.order.id, this.order).subscribe((data:any) => {
        this.order = data;
      });
    }
  }
}
