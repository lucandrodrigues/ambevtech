import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FunctionsUtil } from '../../../util/functions-util';
import { CidadeService } from '../../../service/cidade/cidade.service';
import { Cidade } from '../../../models/cidade';
import { MensagemService } from '../../../service/mensagem.service';

@Component({
    selector: 'app-cidade-form',
    templateUrl: './cidade-form.component.html',
    styleUrls: ['./cidade-form.component.scss']
})
export class CidadeFormComponent implements OnInit, OnChanges {

    // tslint:disable-next-line:no-output-on-prefix
    @Input() abriuModal = false;
    @Output() onSalvar = new EventEmitter();
    public form: FormGroup;
    public cidade: Cidade = new Cidade();

    constructor(private fb: FormBuilder,
        private service: CidadeService,
        private mensagemService: MensagemService) {
        this.form = fb.group({
            id: [null],
            nome: [null, Validators.compose([Validators.required])],
        });
    }

    ngOnInit() {
    }

    ngOnChanges(changes: SimpleChanges) {
        if (changes) {
            if (this.abriuModal === true) {
                this.form.reset();
            }
        }
    }

    public salvar() {
        this.cidade = this.form.getRawValue();
        this.service.salvarCidade(this.cidade).subscribe(
            (response) => {
                this.form.patchValue(response);
                this.mensagemService.sucesso('Atençao', 'Cidade salva com sucesso!');
                this.onSalvar.emit(this.cidade);
            });
    }

}
