import { OrderDetailsComponent } from './pages/order-details/order-details.component';
import { OrderListComponent } from './pages/order-list/order-list.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuardService } from './shared/services';
import { HomeComponent } from './pages/home/home.component';
import { VehicleListComponent } from './pages/vehicle-list/vehicle-list.component';
import { ShopOrdersComponent } from './pages/shop-orders/shop-orders.component';



const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [ AuthGuardService ],
    data:{
      title:'Digital Sign App'
    }
  },
  {
    path:'vehicles',
    component: VehicleListComponent
  },
  {
    path:'orders/:type',
    component:OrderListComponent
  },
  {
    path:'order/:id',
    component:OrderDetailsComponent
  },
  {
    path:'shop',
    component:ShopOrdersComponent
  },
  {
    path: '**',
    redirectTo: 'home'
  }
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: true})],
  providers: [AuthGuardService],
  exports: [RouterModule],
  declarations: [HomeComponent]
})
export class AppRoutingModule { }
