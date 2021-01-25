import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DefaultLayoutComponent} from './containers';
import {HomeComponent} from './views/home/home.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full',
    },
    {
        path: '',
        component: DefaultLayoutComponent,
        data: {
            title: 'Home'
        },
        children: [
            {
                path: 'home',
                component: HomeComponent
            },
            {
                path: 'cidade',
                loadChildren: './views/cidade/cidade.module#CidadeModule'
            },
            {
                path: 'previsao',
                loadChildren: './views/forecast/forecast.module#ForecastModule'
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
