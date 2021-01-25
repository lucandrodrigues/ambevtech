import {RouterModule, Routes} from '@angular/router';
import {CidadeGridComponent} from './cidade-grid/cidade-grid.component';
import {NgModule} from '@angular/core';

const routes: Routes = [
    {
        path: '',
        data: {
            title: 'Cadastros'
        },
        children: [
            {
                path: '',
                component: CidadeGridComponent,
                data: {
                    title: 'Cidade'
                }
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CidadeRoutingModule {
}
