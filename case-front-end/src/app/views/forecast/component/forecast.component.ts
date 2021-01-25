import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {FunctionsUtil} from '../../../util/functions-util';
import {ForecastService} from '../../../service/forecast/forecast.service';
import {Forecast} from '../../../models/forecast';

@Component({
    selector: 'app-forecast',
    templateUrl: './forecast.component.html',
    styleUrls: ['./forecast.component.scss']
})
export class ForecastComponent implements OnInit {

    public nomeCidade = '';
    public forecast: Forecast = new Forecast();

    constructor(public route: ActivatedRoute,
                private service: ForecastService) {
    }

    ngOnInit() {
        this.route.params.subscribe((params) => {
            if (!FunctionsUtil.isEmptyId(params.nomeCidade)) {
                this.nomeCidade = params.nomeCidade;
                this.buscarPrevisaoTempo();
            }
        });
    }

    private buscarPrevisaoTempo() {
        this.service.buscarPrevisao(this.nomeCidade).subscribe(
            (response) => {
                this.forecast = response;
            }
        );
    }

}
