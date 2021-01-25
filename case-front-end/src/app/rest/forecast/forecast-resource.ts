import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {endpointBackend} from '../../util/endpoint-config';
import {Forecast} from '../../models/forecast';

@Injectable({
    providedIn: 'root'
})
export class ForecastResource {
    constructor(private http: HttpClient) {
    }

    public buscarPrevisao(nomeCidade: string): Observable<Forecast> {
        return this.http.get<Forecast>(endpointBackend + 'cidade/previsao', {
            params: new HttpParams().set('nomeCidade', nomeCidade)
        });
    }
}
