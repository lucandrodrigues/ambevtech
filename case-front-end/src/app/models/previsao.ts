export class Previsao {
    nomeCidade: string;
    dias: Dia[];

    constructor() {
        this.dias = [];
    }
}

export class Dia {
    diaDaSemana: string;
    temperatura: Temperatura
    climas: Clima[];
    chuva: number;

    constructor() {
        this.climas = [];
    }
}

export class Temperatura {
    dia: number;
    minima: number;
    maxima: number;
}

export class Clima {
    descricao: string;
    icone: string;
    iconeGrande: string;
}
