import { Component, HostBinding } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, ScreenService, AppInfoService } from './shared/services';
import * as nav from './app-navigation'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent  {

  isDrawerOpen:boolean = true;

  toolbarContent = [{
      widget: 'dxButton',
      location: 'before',
      options: {
        icon: 'menu',
        onClick: () => this.isDrawerOpen = !this.isDrawerOpen,
      },
    },{

    }
  ];

  navigationItems!:any

  @HostBinding('class') get getClass() {
    return Object.keys(this.screen.sizes).filter(cl => this.screen.sizes[cl]).join(' ');
  }

  constructor(private authService: AuthService, private screen: ScreenService, public appInfo: AppInfoService, private router:Router) {
    console.log(nav.navigation)
    this.toggleDrawer = this.toggleDrawer.bind(this);
    this.navigationItems = nav.navigation
  }

  isAuthenticated() {
    return this.authService.loggedIn;
  }

  getAppVersion(){
    return this.appInfo.getVersion();
  }

  toggleDrawer(){
    this.isDrawerOpen = !this.isDrawerOpen
  }

  onItemClick(ev:any){
    if(ev.itemData.path)
      this.router.navigateByUrl(ev.itemData.path);
  }

}
