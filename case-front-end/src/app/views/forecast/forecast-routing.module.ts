import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CidadeGridComponent} from '../cidade/cidade-grid/cidade-grid.component';
import {ForecastComponent} from './component/forecast.component';

const routes: Routes = [
    {
        path: '',
        data: {
            title: 'Previsão do Tempo'
        },
        children: [
            {
                path: '',
                component: ForecastComponent,
                data: {
                    title: 'Previsão'
                }
            },
            {
                path: ':nomeCidade',
                component: ForecastComponent,
                data: {
                    title: 'Previsão'
                }
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ForecastRoutingModule {
}
