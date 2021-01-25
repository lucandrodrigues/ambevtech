
export class FunctionsUtil {

    static validarEmail(email: string): boolean {
        if (this.isEmpty(email)) {
            return false;
        }

        // tslint:disable-next-line:max-line-length
        const regex = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
        return regex.test(email);
    }

    static isEmpty(text: string): boolean {
        return (!text || text.trim().length <= 0);
    }

    static isEmptyId(id: number): boolean {
        return (!id || id <= 0);
    }

    static validarCPF(cpf: string): boolean {
        if (this.isEmpty(cpf)) {
            return false;
        }

        // tslint:disable-next-line:max-line-length
        const regex = new RegExp(/([0-9]{2}[\.]?[0-9]{3}[\.]?[0-9]{3}[\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\.]?[0-9]{3}[\.]?[0-9]{3}[-]?[0-9]{2})/g);
        return regex.test(cpf);
    }

    static validarCNPJ(cnpj: string): boolean {
        if (this.isEmpty(cnpj)) {
            return false;
        }

        // tslint:disable-next-line:max-line-length
        const regex = new RegExp(/^\d{2}\.\d{3}\.\d{3}\/\d{4}\-\d{2}$/);
        return regex.test(cnpj);
    }

    static validarCEP(cep: string): boolean {
        if (this.isEmpty(cep)) {
            return false;
        }

        const regex = new RegExp(/[0-9]{5}-[0-9]{3}/);
        return regex.test(cep);
    }

}
