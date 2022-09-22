import { VehicleOrder, OrdersService, Deficiency } from './../../services/orders.service';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-order-deficiencies',
  templateUrl: './order-deficiencies.component.html',
  styleUrls: ['./order-deficiencies.component.scss']
})
export class OrderDeficienciesComponent implements OnInit {

  @Input()
  deficiencies:Deficiency[] = []

  @Input()
  order!:VehicleOrder | null;

  constructor(private service:OrdersService) { }

  ngOnInit(): void {
  }

  addDeficiency(){
    let def:Deficiency = {
      description:'',
      order:this.order
    }
    this.deficiencies.push(def);
  }

  removeDeficiency(item:any){
    this.deficiencies.splice(this.deficiencies.indexOf(item), 1)
  }

}
