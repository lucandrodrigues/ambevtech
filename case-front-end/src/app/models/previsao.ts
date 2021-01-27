export class Previsao {
    nomeCidade: string;
    dias: Dia[];

    constructor() {
        this.dias = [];
    }
}

export class Dia {
    data: string;
    temperatura: Temperatura
    climas: Clima[];

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
}
