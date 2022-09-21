import { Vehicle } from './../../services/vehicle.service';
import { VehicleOrder, OrdersService } from './../../services/orders.service';
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import notify from 'devextreme/ui/notify';

@Component({
  selector: 'app-vehicle-orders',
  templateUrl: './vehicle-orders.component.html',
  styleUrls: ['./vehicle-orders.component.scss']
})
export class VehicleOrdersComponent implements OnInit, OnChanges {

  @Input()
  vehicle!:Vehicle | null | undefined;

  orders:VehicleOrder[] = [];

  constructor(private service:OrdersService) { }

  ngOnChanges(changes: SimpleChanges): void {
      if(changes.vehicle){
        this.refreshData()
      }
  }

  ngOnInit(): void {
  }

  getGridHeight(){
    return (window.innerHeight - 10) /2
  }

  refreshData(){
    if(!this.vehicle) return;
    this.service.getVehicleOrders(this.vehicle.id).subscribe((data:any) => {
      this.orders = data;
    })
  }

  handleSaveOrder(ev:any){
    console.log(ev);
    const operation = ev.changes[0] || null
    if(operation){
      switch(operation.type){
        case 'insert':
          operation.data.id = null;
          operation.data.vehicle = this.vehicle;
          this.service.addVehicleOrder(operation.data).subscribe(() => {
            notify("Record saved!", "success", 500);
          });
          break;
        case 'update':
          this.service.updateVehicleOrder(operation.key,operation.data).subscribe(() => {
            notify("Record saved!", "success", 500);
          });
          break;
        case 'remove':
          this.service.deleteVehicleOrder(operation.key).subscribe(() => {
            notify("Record deleted!", "success", 500);
          });
          break;
      }
    }
  }

}
