import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AgGridModule } from 'ag-grid-angular';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { VsmGridComponent } from './vsm-grid.component';


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        AgGridModule.withComponents([]),
        PaginationModule.forRoot()
    ],
    declarations: [
        VsmGridComponent
    ],
    exports: [
        AgGridModule,
        VsmGridComponent,
        PaginationModule
    ]
})
export class VsmGridModule { }
