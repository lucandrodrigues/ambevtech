import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class MensagemService {

    static mensagens = new EventEmitter<any>();

    mostrar(msg: any) {
        if (!msg || !msg.error) {
            msg.tipo = 'erro';
            msg.titulo = 'Erro';
            msg.descricao = 'Não foi possível realizar operação.';
        }

        if (msg.error && (!msg.error.tipo || !msg.error.titulo)) {
            msg.tipo = 'erro';
            msg.titulo = 'Erro';
            msg.descricao = msg.error.error_description ? msg.error.error_description : msg.error;

            if (msg.descricao instanceof Object) {
                msg.descricao = 'Não foi possível realizar operação.';
            }

            if (msg.descricao.includes('<html')) {
                msg.descricao = 'Ocorreu um erro inesperado, por favor, entre em contato com nosso suporte.';
            }
        } else {
            msg = msg.error ? msg.error : msg;
        }

        let tipo = 'info';
        if (msg.tipo) {
            if (msg.tipo.trim().toLowerCase() === 'success' ||
                msg.tipo.trim().toLowerCase() === 'sucesso' ||
                msg.tipo.trim().toLowerCase() === 'ok') {
                tipo = 'success';
            }

            if (msg.tipo.trim().toLowerCase() === 'aviso' ||
                msg.tipo.trim().toLowerCase() === 'warning' ||
                msg.tipo.trim().toLowerCase() === 'warn') {
                tipo = 'warn';
            }

            if (msg.tipo.trim().toLowerCase() === 'erro' ||
                msg.tipo.trim().toLowerCase() === 'error' ||
                msg.tipo.trim().toLowerCase() === 'falha') {
                tipo = 'error';
            }
        }

        msg.tipo = tipo;

        if (!msg.titulo) {
            msg.titulo = 'Informação';
        }

        if (!msg.descricao) {
            msg.descricao = msg.error;
        }

        MensagemService.mensagens.emit({
            tipo: tipo,
            titulo: msg.titulo,
            descricao: msg.descricao
        });
    }

    aviso(titulo: string, descricao: string) {
        MensagemService.mensagens.emit({
            tipo: 'warn',
            titulo: titulo,
            descricao: descricao
        });
    }

    erro(titulo: string, descricao: string) {
        MensagemService.mensagens.emit({
            tipo: 'error',
            titulo: titulo,
            descricao: descricao
        });
    }

    sucesso(titulo: string, descricao: string) {
        MensagemService.mensagens.emit({
            tipo: 'success',
            titulo: titulo,
            descricao: descricao
        });
    }

    info(titulo: string, descricao: string) {
        MensagemService.mensagens.emit({
            tipo: 'info',
            titulo: titulo,
            descricao: descricao
        });
    }

}
