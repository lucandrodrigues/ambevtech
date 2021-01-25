import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanActivateChild {

    constructor(private router: Router) {

    }

    canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        return this.checkLogin(state.url);
    }

    canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        return this.canActivate(childRoute, state);
    }

    private checkLogin(url: string): Observable<boolean> {
        console.log(url);

        const isLoggedUser = localStorage.getItem('isLoggedUser');

        return of(isLoggedUser).pipe(
            map(
                response => {
                    return true;
                }
            ),
            catchError(
                error => {
                    return of(false);
                }
            )
        );


        // const navigationExtras: NavigationExtras = {
        //     queryParams: { 'session_id': sessionId },
        //     fragment: 'anchor'
        // };

        // this.router.navigate(['/login'], navigationExtras);
        // return false;
    }

}
