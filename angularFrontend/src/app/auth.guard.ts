import {CanActivate, Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {RestService} from './services/rest.service';
import {ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router/src/router_state';

@Injectable({
    providedIn: 'root'
  })
export class NeedAuthGuard implements CanActivate {

  constructor(private restService: RestService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    const redirectUrl = route['_routerState']['url'];
    console.log("Checking loggedin :" + this.restService.isLogged());
    if (this.restService.isLogged()) {
      return true;
    }

    this.router.navigateByUrl(
      this.router.createUrlTree(
        ['/login'], {
          queryParams: {
            redirectUrl
          }
        }
      )
    );

    return false;
  }
}