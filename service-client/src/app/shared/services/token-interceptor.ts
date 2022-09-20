import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import {environment} from "../../../environments/environment";

import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import * as config from './../../config';

declare var window:any

export function isInDevMode():boolean {
  return !environment.production;
}

const serverConfig = config as any
const env = isInDevMode() ? 'dev' : 'prod'

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private router: Router) {}
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    //request.url = `${config.devUrl}/${request.url}`
    request = request.clone({
      url:`${serverConfig.server[env]}/${request.url}`,
      // setHeaders: {
      //   'X-APIKEY': this.service.getAppUser()
      // }
    });
    return next.handle(request).pipe(tap(()=>{}, (err:any)=>{
      if (err instanceof HttpErrorResponse) {
        if (err.status !== 401) {
         return;
        }
        //this.auth.setAutorize(false)
      }
    }));
  }
}
