import { AfterViewInit, Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { GridOptions } from 'ag-grid-community';

@Component({
    selector: 'app-vsm-grid',
    templateUrl: './vsm-grid.component.html',
    styleUrls: ['./vsm-grid.component.css']
})
export class VsmGridComponent implements OnInit, AfterViewInit {

    @ViewChild('textoPesquisa') textoPesquisa: ElementRef;

    @Input() pagina = 1;
    @Input() gridOptions: GridOptions;
    @Input() rowData: any = [];
    @Input() textoInput = 'Pesquisar...';
    // @Input() ajustarColunas = false;
    @Input() mostrarPesquisar = false;
    @Input() mostrarFiltros = false;

    // tslint:disable-next-line:no-output-on-prefix
    @Output() onPesquisar: EventEmitter<string> = new EventEmitter();
    // tslint:disable-next-line:no-output-on-prefix
    @Output() onPaginar: EventEmitter<number> = new EventEmitter();
    // tslint:disable-next-line:no-output-on-prefix
    @Output() onDuploClick: EventEmitter<any> = new EventEmitter();
    // tslint:disable-next-line:no-output-on-prefix
    @Output() onGridReady: EventEmitter<any> = new EventEmitter();
    // tslint:disable-next-line:no-output-on-prefix
    @Output() onSelecionar: EventEmitter<any> = new EventEmitter();
    // tslint:disable-next-line:no-output-on-prefix
    @Output() onSelectionChanged: EventEmitter<any> = new EventEmitter();
    // tslint:disable-next-line:no-output-on-prefix
    @Output() onFiltrar: EventEmitter<any> = new EventEmitter();

    inputPesquisar: string;

    static getDefaultGridOptions(): GridOptions {
        const grid: GridOptions = {};
        grid.context = <GridOptions>{
            context: {
                componentParent: this
            },
            onGridReady: (params) => {
                if (grid && grid.api) {
                    grid.api.sizeColumnsToFit();
                }
            }
        };
        // this.isFullWidthCell = function(rowNode) {
        //     return rowNode.data.fullWidth;
        //   };
        // grid.enableColResize = true;

        grid.suppressRowClickSelection = true;
        // grid.rowSelection = 'multiple';
        grid.rowMultiSelectWithClick = true;

        // grid.enableSorting = true;
        // grid.defaultColDef.sortable = true;
        grid.domLayout = 'autoHeight';
        grid.overlayLoadingTemplate = '<span class="ag-bl-overlay">Aguarde, carregando...</span>';
        grid.overlayNoRowsTemplate = '<span class="ag-bl-overlay">Nenhum registro encontrado...</span>';

        return grid;
    }

    constructor(private element: ElementRef) {}

    ngOnInit() {

    }

    ngAfterViewInit() {

    }

    gridReady(event) {
        this.gridOptions.api.sizeColumnsToFit();
        this.onGridReady.emit(event);

        setTimeout(function() {
            event.api.resetRowHeights();
          }, 2000);
    }

    pesquisar(event) {
        if (!event) {
            return this.onPesquisar.emit(this.inputPesquisar);
        }

        if (event.target.localName === 'span') {
            return this.onPesquisar.emit(this.textoPesquisa.nativeElement.value);
        }

        if (event.target.localName === 'i') {
            return this.onPesquisar.emit(this.textoPesquisa.nativeElement.value);
        }

        return this.onPesquisar.emit(event.target.value);
    }

    paginar(event) {
        if (this.pagina !== event.page) {
            this.pagina = event.page;
            return this.onPaginar.emit(this.pagina);
        }
    }

    duploClick(event) {
        return this.onDuploClick.emit(event.data);
    }

    select(event) {
        if (this.gridOptions.api) {
            this.onSelecionar.emit(this.gridOptions.api.getSelectedRows());
        }
    }

    selectionChanged(event) {
        return this.onSelectionChanged.emit(event);
    }

    filtrar() {
        this.onFiltrar.emit();
    }

}
