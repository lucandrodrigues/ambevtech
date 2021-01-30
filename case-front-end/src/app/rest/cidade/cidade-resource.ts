import { Previsao } from './../../models/previsao';
import {HttpClient} from '@angular/common/http';
import {Cidade} from '../../models/cidade';
import {Observable} from 'rxjs';
import {endpointBackend} from '../../util/endpoint-config';
import {FiltroUtil} from '../../util/filtro-util';
import {Injectable} from '@angular/core';
import { Page } from '../../util/page';

@Injectable({
    providedIn: 'root'
})
export class CidadeResource {

    constructor(private http: HttpClient) {
    }

    public salvarCidade(cidade: Cidade): Observable<Cidade> {
        return this.http.post<Cidade>(endpointBackend + 'cidade', cidade);
    }

    public listarCidades(filtro: FiltroUtil): Observable<Page<Cidade>> {
        return this.http.post<Page<Cidade>>(endpointBackend + 'cidade/listar', filtro);
    }

    public buscarPrevisao(cidadeId: number): Observable<Previsao> {
        return this.http.get<Previsao>(endpointBackend + 'cidade/previsao/'+ cidadeId.toString());
    }
}
