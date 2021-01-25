import {HttpClient, HttpParams} from '@angular/common/http';
import {Cidade} from '../../models/cidade';
import {Observable} from 'rxjs';
import {endpointBackend} from '../../util/endpoint-config';
import {FiltroUtil} from '../../util/filtro-util';
import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class CidadeResource {

    constructor(private http: HttpClient) {
    }

    public salvarCidade(cidade: Cidade): Observable<Cidade> {
        return this.http.post<Cidade>(endpointBackend + 'cidade', cidade);
    }

    public listarCidades(filtro: FiltroUtil): Observable<Array<Cidade>> {
        return this.http.post<Array<Cidade>>(endpointBackend + 'cidade/listar', filtro);
    }

    buscarPorId(cidadeId: number): Observable<Cidade> {
        return this.http.get<Cidade>(endpointBackend + 'cidade/' + cidadeId);
    }
}
