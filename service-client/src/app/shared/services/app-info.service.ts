import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs/internal/Observable';

declare var window:any

export interface AppMenuItem {
  id:string,
  label:string,
  icon:string,
  routerLink:string,
  parent:number,
  items:AppMenuItem[]
}

export interface AppUser{
  username:string,
  roles:any[],
  delegatedRole?:number
}

@Injectable()
export class AppInfoService {

  appUser:AppUser

  currentRole:BehaviorSubject<number> = new BehaviorSubject<number>(0);

  constructor(private http:HttpClient) {
    const username = window.nw? window.nw.require('os').userInfo().username:'cristian_mar'; // TODO: Get from system user
    this.appUser = {
      username:username,
      roles:[]
    }
  }

  private getDelegatedRole(){
    const delegatedRoleData = localStorage.getItem('delegatedRole')
    if(delegatedRoleData) return parseInt(delegatedRoleData)
    // get from localstorage or first role if not set!
    localStorage.setItem('delegatedRole',this.appUser?.roles[0].rolid?.toString())
    return this.appUser?.roles[0].rolid
  }


  public get title() {
    return 'My Auto Service Shop';
  }

  public get currentYear() {
    return new Date().getFullYear();
  }

  public getAppMenu(role:any):Promise<AppMenuItem[]>{
    return this.http.get<AppMenuItem[]>(`menu/${role}`).toPromise();
  }

  public async getUserInfo():Promise<AppUser>{
    return new Promise((resolve) => {
      if(this.appUser.roles.length == 0){
        this.http.get<any[]>(`roles/${this.appUser.username}`).toPromise().then(resp =>{
          this.appUser.roles = resp;
          this.appUser.delegatedRole = this.getDelegatedRole()
          resolve(this.appUser)
        })
      }else{
        resolve(this.appUser)
      }
    })

  }

  public setUserDelegatedRole(role:number){
    this.appUser.delegatedRole = role;
    this.currentRole.next(role)
    localStorage.setItem('delegatedRole',role.toString())
  }

  getCurrentRole(): Observable<number> {
    return this.currentRole.asObservable();
  }

  getVersion(){
    if(window.nw){
      return window.nw.App.manifest.version
    }else{
      return "Browser version"
    }
  }
}
