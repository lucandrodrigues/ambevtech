import { Previsao } from './../../../../models/previsao';
import { CidadeService } from './../../../../service/cidade/cidade.service';
import { FunctionsUtil } from './../../../../util/functions-util';
import { Component, OnInit, Input, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-cidade-previsao',
  templateUrl: './cidade-previsao.component.html',
  styleUrls: ['./cidade-previsao.component.scss']
})
export class CidadePrevisaoComponent implements OnInit {

  @Input() cidadeId;
  @Input() abriuModal = false;
  public previsao: Previsao = new Previsao();

  constructor(private service: CidadeService) { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes) {
      if (this.abriuModal === true) {
        if (!FunctionsUtil.isEmptyId(this.cidadeId)) {
          this.service.buscarPrevisao(this.cidadeId).subscribe(
            (response) => {
              this.previsao = response;
            }
          );
        }
      }
    }
  }

}
