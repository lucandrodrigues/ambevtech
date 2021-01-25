export class Forecast {
    nomeCidade: string;
    temperatura: Temperatura[];

    constructor() {
        this.temperatura = [];
    }
}

export class Temperatura {
    data: string;

    hora: string;

    temperatura: number;

    temperaturaMaxima: number;

    temperaturaMinima: number;

    clima: Clima;

    constructor() {
        this.clima = new Clima();
    }
}

export class Clima {
    previsao: string;
    urlIcone: string;
}
