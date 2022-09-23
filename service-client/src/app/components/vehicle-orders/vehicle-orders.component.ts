import { Vehicle } from './../../services/vehicle.service';
import { VehicleOrder, OrdersService, Deficiency } from './../../services/orders.service';
import { Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import notify from 'devextreme/ui/notify';
import { DxDataGridComponent } from 'devextreme-angular';

@Component({
  selector: 'app-vehicle-orders',
  templateUrl: './vehicle-orders.component.html',
  styleUrls: ['./vehicle-orders.component.scss']
})
export class VehicleOrdersComponent implements OnInit, OnChanges, OnDestroy {

  @Input()
  vehicle!:Vehicle | null | undefined;

  orders:VehicleOrder[] = [];

  editingOrder!:VehicleOrder | null;
  editingDeficiencies:Deficiency[] = []

  statuses:any[] = [
    { id: 'open', text: 'OPEN' },
    { id: 'closed', text: 'CLOSED' },
    { id: 'sent', text: 'SENT' },
    { id: 'partsReceived', text: 'PARTS Received' },
    { id: 'vehicleReceived', text: 'VEHICLE RECEPTION' },
    { id: 'partsOrdered', text: 'WAITING PARTS' },
  ]

  @ViewChild("grid") grid!:DxDataGridComponent
  isSendingOrder!: boolean;
  stompClient: any;

  constructor(private service:OrdersService) {
    this.sendOrder = this.sendOrder.bind(this)
    this.saveOrder = this.saveOrder.bind(this)
    this.cancelEdit = this.cancelEdit.bind(this)
    this.stompClient = service.getSocket();
    this.stompClient.connect({}, () => {
      console.log("WS connected");
      this.stompClient.subscribe("/topic/msg", (msg:any) => {
        console.log("message received " + msg);
        this.refreshData();
      })
    })
  }
  ngOnDestroy(): void {
    //this.stompClient.disconnect()
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

  setCurrentOrder(ev:any){
    console.log(ev);
    if(typeof ev.data.id != 'number') ev.data.id = null;
    this.editingOrder = ev.data;
  }

  isEditable(e:any) {
    return e.row.data.status == 'open';
  }

  sendOrder(ev:any){
    if(this.editingOrder){
      this.service.sendVehicleOrder(this.editingOrder?.id, this.editingOrder).subscribe(() => {
        notify("Record saved!", "success", 500);
        this.refreshData()
        this.cancelEdit()
      });
    }
    //console.log(this.editingDeficiencies);
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
            this.service.updateVehicleOrder(operation.key,operation.data).subscribe(() => {
              notify("Record saved!", "success", 500);
              this.refreshData()
            });
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
