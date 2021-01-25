import {Injectable} from '@angular/core';
import {Observable, throwError} from 'rxjs';
import {ForecastResource} from '../../rest/forecast/forecast-resource';
import {FunctionsUtil} from '../../util/functions-util';
import {Forecast} from '../../models/forecast';

@Injectable({
    providedIn: 'root'
})
export class ForecastService {

    constructor(private resource: ForecastResource) {
    }

    public buscarPrevisao(nomeCidade: string): Observable<Forecast> {
        if (FunctionsUtil.isEmpty(nomeCidade)) {
            return throwError('Cidade deve ser informada');
        }
        return this.resource.buscarPrevisao(nomeCidade);
    }
}
