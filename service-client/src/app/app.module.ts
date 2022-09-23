import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {AuthService, ScreenService, AppInfoService} from './shared/services';
import { AppRoutingModule } from './app-routing.module';
import {
  DxButtonModule, DxChartModule,
  DxCheckBoxModule,
  DxDataGridModule, DxDateBoxModule, DxDropDownButtonModule,
  DxFileUploaderModule, DxFormModule, DxLoadPanelModule, DxLookupModule,
  DxMenuModule,
  DxNumberBoxModule, DxPopupModule, DxScrollViewModule,
  DxSelectBoxModule,
  DxTabPanelModule, DxTextAreaModule, DxTextBoxComponent, DxTextBoxModule, DxToolbarModule, DxTreeListModule, DxValidatorModule,
  DxListModule, DxDrawerModule, DxTreeViewModule
} from "devextreme-angular";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenInterceptor} from "./shared/services/token-interceptor";
import {StringToDate} from "./shared/utils/string-to-date";
import { VehicleListComponent } from './pages/vehicle-list/vehicle-list.component';
import { VehicleOrdersComponent } from './components/vehicle-orders/vehicle-orders.component';
import { OrderDeficienciesComponent } from './components/order-deficiencies/order-deficiencies.component';
import { OrderListComponent } from './pages/order-list/order-list.component';
import { OrderDetailsComponent } from './pages/order-details/order-details.component';
import { OrderStatusPipe } from './pipes/order-status.pipe';
import { ShopOrdersComponent } from './pages/shop-orders/shop-orders.component';

@NgModule({
  declarations: [
    AppComponent,
    VehicleListComponent,
    VehicleOrdersComponent,
    OrderDeficienciesComponent,
    OrderListComponent,
    OrderDetailsComponent,
    OrderStatusPipe,
    ShopOrdersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DxDataGridModule,
    HttpClientModule,
    DxMenuModule,
    DxButtonModule,
    DxPopupModule,
    DxFileUploaderModule,
    DxTextBoxModule,
    DxDataGridModule,
    DxTextAreaModule,
    DxToolbarModule,
    DxSelectBoxModule,
    DxValidatorModule,
    DxCheckBoxModule,
    DxListModule,
    DxTreeViewModule,
    DxDrawerModule,
    DxFormModule
  ],
  providers: [
    // {
    //   provide: HTTP_INTERCEPTORS,
    //   useClass: TokenInterceptor,
    //   multi: true
    // },
    StringToDate,
    AuthService,
    ScreenService,
    AppInfoService,
    HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
