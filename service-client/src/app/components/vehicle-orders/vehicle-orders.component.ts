import { Vehicle } from './../../services/vehicle.service';
import { VehicleOrder, OrdersService } from './../../services/orders.service';
import { Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import notify from 'devextreme/ui/notify';
import { DxDataGridComponent } from 'devextreme-angular';

@Component({
  selector: 'app-vehicle-orders',
  templateUrl: './vehicle-orders.component.html',
  styleUrls: ['./vehicle-orders.component.scss']
})
export class VehicleOrdersComponent implements OnInit, OnChanges {

  @Input()
  vehicle!:Vehicle | null | undefined;

  orders:VehicleOrder[] = [];

  @ViewChild("grid") grid!:DxDataGridComponent
  isSendingOrder!: boolean;

  constructor(private service:OrdersService) {
    this.sendOrder = this.sendOrder.bind(this)
    this.saveOrder = this.saveOrder.bind(this)
    this.cancelEdit = this.cancelEdit.bind(this)
  }

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

  sendOrder(ev:any){
    this.isSendingOrder = true;
    this.grid.instance.saveEditData()
  }

  saveOrder(){
    this.grid.instance.saveEditData()
  }

  cancelEdit(){
    this.grid.instance.cancelEditData()
  }

  handleSaveOrder(ev:any){
    console.log(this.isSendingOrder);
    const operation = ev.changes[0] || null
    if(operation){
      switch(operation.type){
        case 'insert':
          operation.data.id = null;
          operation.data.vehicle = this.vehicle;
          this.service.addVehicleOrder(operation.data).subscribe(() => {
            notify("Record saved!", "success", 500);
            this.refreshData()
          });
          break;
        case 'update':
          if(this.isSendingOrder){
            this.service.sendVehicleOrder(operation.key,operation.data).subscribe(() => {
              notify("Record saved!", "success", 500);
              this.refreshData()
            });
          }else{
            this.service.updateVehicleOrder(operation.key,operation.data).subscribe(() => {
              notify("Record saved!", "success", 500);
              this.refreshData()
            });
          }
          break;
        case 'remove':
          this.service.deleteVehicleOrder(operation.key).subscribe(() => {
            notify("Record deleted!", "success", 500);
          });
          break;
      }
    }
    this.isSendingOrder = false
  }

}
