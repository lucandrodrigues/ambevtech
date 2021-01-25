import {Injectable} from '@angular/core';
import {CidadeResource} from '../../rest/cidade/cidade-resource';
import {Cidade} from '../../models/cidade';
import {Observable, throwError} from 'rxjs';
import {FiltroUtil} from '../../util/filtro-util';
import {FunctionsUtil} from '../../util/functions-util';

@Injectable({
    providedIn: 'root'
})
export class CidadeService {

    constructor(private resource: CidadeResource) {
    }

    public salvarCidade(cidade: Cidade): Observable<Cidade> {
        if (!cidade || cidade === null) {
            return throwError('Cidade deve ser informada');
        }
        return this.resource.salvarCidade(cidade);
    }

    public listarCidades(filtro: FiltroUtil): Observable<Array<Cidade>> {
        if (!filtro || filtro === null) {
            return throwError('Filtros devem ser informados');
        }
        return this.resource.listarCidades(filtro);
    }

    public buscarPorId(cidadeId: number): Observable<Cidade> {
        if (FunctionsUtil.isEmptyId(cidadeId)) {
            return throwError('Cidade deve ser informada');
        }
        return this.resource.buscarPorId(cidadeId);
    }
}
