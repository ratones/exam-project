import { Vehicle } from './../../services/vehicle.service';
import { Component, OnInit } from '@angular/core';
import { VehicleService } from 'src/app/services/vehicle.service';
import notify from 'devextreme/ui/notify';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.scss']
})
export class VehicleListComponent implements OnInit {
  vehicles!: Vehicle[];

  selectedRows:any[] = []

  constructor(private service:VehicleService) {
    this.refreshData()
  }

  ngOnInit(): void {
  }

  refreshData(){
    this.service.getVehicles().subscribe((data:any) => {
      this.vehicles = data.content;
    })
  }

  handleSaveVehicle(ev:any){
    console.log(ev);
    const operation = ev.changes[0] || null
    if(operation){
      switch(operation.type){
        case 'insert':
          operation.data.id = null;
          this.service.insertVehicle(operation.data).subscribe(() => {
            notify("Record saved!", "success", 500);
          });
          break;
        case 'update':
          this.service.updateVehicle(operation.key,operation.data).subscribe(() => {
            notify("Record saved!", "success", 500);
          });
          break;
        case 'remove':
          this.service.removeVehicle(operation.key).subscribe(() => {
            notify("Record deleted!", "success", 500);
          });
          break;
      }
    }
  }

  viewOrders(){}
}
