import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ForecastRoutingModule} from './forecast-routing.module';
import {ForecastComponent} from './component/forecast.component';
import {FormValidatorDirective} from '../../util/form-validator.directive';
import {VsmGridModule} from '../../componentes/vsm-grid/vsm-grid.module';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
    declarations: [
        ForecastComponent,
    ],
    imports: [
        CommonModule,
        ForecastRoutingModule
    ]
})
export class ForecastModule {
}
