import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CidadeFormComponent} from './cidade-form/cidade-form.component';
import {CidadeGridComponent} from './cidade-grid/cidade-grid.component';
import {CidadeRoutingModule} from './cidade-routing.module.';
import {VsmGridModule} from '../../componentes/vsm-grid/vsm-grid.module';
import {OverlayComponent} from '../../componentes/overlay/overlay.component';
import {ReactiveFormsModule} from '@angular/forms';
import {FormValidatorDirective} from '../../util/form-validator.directive';
import {NgSelectModule} from '@ng-select/ng-select';

@NgModule({
    declarations: [
        CidadeFormComponent,
        CidadeGridComponent,
        OverlayComponent,
        FormValidatorDirective
    ],
    imports: [
        CommonModule,
        VsmGridModule,
        CidadeRoutingModule,
        ReactiveFormsModule,
        NgSelectModule
    ],
    exports: [
        CidadeFormComponent,
        CidadeGridComponent
    ]
})
export class CidadeModule {
}
