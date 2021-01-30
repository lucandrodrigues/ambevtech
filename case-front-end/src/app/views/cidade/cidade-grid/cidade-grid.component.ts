import { FormGroup, FormBuilder } from '@angular/forms';
import { Page } from './../../../util/page';
import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { CidadeService } from '../../../service/cidade/cidade.service';
import { Cidade } from '../../../models/cidade';
import { FiltroUtil } from '../../../util/filtro-util';
import { MensagemService } from '../../../service/mensagem.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-cidade-grid',
    templateUrl: './cidade-grid.component.html',
    styleUrls: ['./cidade-grid.component.scss']
})
export class CidadeGridComponent implements OnInit {

    @ViewChild('foco') foco: ElementRef;

    public cidades: Page<Cidade> = new Page<Cidade>(new Array<Cidade>(), 0);
    public filtro: FiltroUtil = new FiltroUtil();
    public cidadeSelecionada = new Cidade();
    public showSidebar = false;
    public showSidebarPrevisao = false;
    public paginaSelecionada = 1;
    public formFiltro: FormGroup;

    constructor(
        private service: CidadeService,
        public fb: FormBuilder
    ) {
        this.formFiltro = fb.group({
            nome: [null]
        });
    }

    ngOnInit() {
        this.foco.nativeElement.focus();
        this.filtro.obj = new Cidade();
        this.filtrar();
    }

    public pesquisar() {
        this.filtro.obj = this.formFiltro.getRawValue();
        this.filtrar();
    }

    paginar(event) {
        this.paginaSelecionada = event.page;
        this.filtro.page = event.page - 1;
        this.filtrar();
    }

    public duploClick(event) {
        this.cidadeSelecionada = event;
        this.showSidebarPrevisao = true;
    }

    public novaCidade() {
        this.cidadeSelecionada = new Cidade();
        this.showSidebar = true;
    }

    public salvarCidade() {
        this.showSidebar = false;
        this.filtrar();
    }

    public mostrarPrevisaoTempo(id: number) {
        this.cidadeSelecionada.id = id;
        this.showSidebarPrevisao = true;
    }

    private filtrar() {
        this.service.listarCidades(this.filtro).subscribe(
            (response) => {
                this.cidades = response;
            });
    }

}
