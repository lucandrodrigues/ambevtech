import {Component, OnInit} from '@angular/core';
import {GridOptions} from 'ag-grid-community';
import {VsmGridComponent} from '../../../componentes/vsm-grid/vsm-grid.component';
import {CidadeService} from '../../../service/cidade/cidade.service';
import {Cidade} from '../../../models/cidade';
import {FiltroUtil} from '../../../util/filtro-util';
import {MensagemService} from '../../../service/mensagem.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-cidade-grid',
    templateUrl: './cidade-grid.component.html',
    styleUrls: ['./cidade-grid.component.scss']
})
export class CidadeGridComponent implements OnInit {

    public gridOptions: GridOptions = VsmGridComponent.getDefaultGridOptions();
    public rowData: Cidade[] = [];
    public filtro: FiltroUtil = new FiltroUtil();
    public cidadeSelecionada = new Cidade();
    public showSidebar = false;

    constructor(private service: CidadeService,
                private mensagemService: MensagemService,
                private router: Router) {
        this.gridOptions.columnDefs = [
            {
                headerName: '',
                width: 20,
                headerCheckboxSelection: true,
                checkboxSelection: true,
                headerCheckboxSelectionFilteredOnly: true
            },
            {
                headerName: 'Nome',
                field: 'nome',
                width: 150
            },
            {
                headerName: 'UF',
                field: 'uf',
                width: 100
            },
        ];
    }

    ngOnInit() {
        this.filtro.obj = '';
    }

    public pesquisar(event) {
        this.filtro.obj = event;
        this.filtrar();
    }

    public paginar(event) {
        this.filtro.page = event;
        this.filtrar();
    }

    public duploClick(event) {
        this.cidadeSelecionada = event;
        this.showSidebar = true;
    }

    public novaCidade() {
        this.cidadeSelecionada = new Cidade();
        this.showSidebar = true;
    }

    public abrirForm() {
        if (this.validarSelecaoGrid()) {
            this.cidadeSelecionada = this.gridOptions.api.getSelectedRows()[0];
            this.showSidebar = true;
        }
    }

    public salvarCliente() {
        this.showSidebar = false;
        this.filtrar();
    }

    public abrirPrevisaoTempo() {
        if (this.validarSelecaoGrid()) {
            this.cidadeSelecionada = this.gridOptions.api.getSelectedRows()[0];
            this.router.navigate(['/previsao' , this.cidadeSelecionada.nome]);
        }
    }

    private filtrar() {
        this.service.listarCidades(this.filtro).subscribe(
            (response) => {
                this.rowData = response;
            });
    }

    private validarSelecaoGrid() {
        if (this.gridOptions.api.getSelectedRows().length <= 0) {
            this.mensagemService.aviso('Atenção', 'Nenhum registro selecionado');
            return false;
        }
        return true;
    }
}
