export class FiltroUtil {
    obj: any;
    page: number;
    size: number;
    sort: any;
    textoPesquisar: string;

    constructor(paginaselecionada = 0) {
        this.page = paginaselecionada;
        this.size = 7;
    }
}

export class CustomSizeFiltroUtil extends FiltroUtil {
    constructor(size: number) {
        super();
        this.size = size;
    }
}
