import {Component, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {MensagemService} from '../../service/mensagem.service';

@Component({
    selector: 'app-mensagem',
    templateUrl: './mensagem.component.html',
    styleUrls: ['./mensagem.component.css']
})
export class MensagemComponent implements OnInit {

    msg: any = {
        tipo: '',
        titulo: '',
        descricao: '',
        disableTimeOut: false
    };

    constructor(private service: ToastrService) {

        const defaultOptions = {
            preventDuplicates: true,
            enableHtml: true,
            disableTimeOut: false,
            closeButton: true,
            tapToDismiss: false,
            maxOpened: 3,
            timeOut: 2000
        };

        const defaultOptionsCloseClick = {
            preventDuplicates: true,
            enableHtml: true,
            disableTimeOut: true,
            closeButton: true,
            tapToDismiss: true,
            maxOpened: 3,
        };

        const defaultOptionsError = {
            preventDuplicates: true,
            enableHtml: true,
            disableTimeOut: true,
            closeButton: true,
            tapToDismiss: false,
            maxOpened: 3,
        };

        MensagemService.mensagens.subscribe(
            value => {
                this.msg = value;

                switch (this.msg.tipo) {
                    case 'info':
                        this.service.info(this.msg.descricao, this.msg.titulo, defaultOptions);
                        break;
                    case 'success':
                        this.service.success(this.msg.descricao, this.msg.titulo, defaultOptions);
                        break;
                    case 'error':
                        this.service.error(this.msg.descricao, this.msg.titulo, defaultOptionsError);
                        break;
                    case 'warn':
                        this.service.warning(this.msg.descricao, this.msg.titulo,
                            this.msg.disableTimeOut ? defaultOptionsCloseClick : defaultOptions);
                        break;
                }
            },
            () => {
                this.msg = {
                    tipo: '',
                    titulo: '',
                    descricao: '',
                    disableTimeOut: false
                };
            }
        );

    }

    ngOnInit() {
    }

}
