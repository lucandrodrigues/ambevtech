import {HttpClient, HttpInterceptor, HttpResponse} from '@angular/common/http';
import {HttpHandler} from '@angular/common/http/src/backend';
import {HttpRequest} from '@angular/common/http/src/request';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {finalize, tap} from 'rxjs/operators';
import {MensagemService} from '../service/mensagem.service';
import {LoadingComponent} from '../componentes/loading/loading.component';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    private processosEmExecucao = 0;

    constructor(
        private http: HttpClient,
        private mensagemService: MensagemService,
        private router: Router) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler) {
        LoadingComponent.show();
        this.processosEmExecucao++;

        const request = req.clone({
            // headers: req.headers.set('Authorization', 'Bearer ' + localStorage.getItem('token'))
        });

        // @ts-ignore
        return next.handle(request).pipe(
            tap(
                event => {
                    if (event instanceof HttpResponse) {
                        this.removerProcessoLoading();
                    }
                },
                error => {
                    this.removerProcessoLoading();
                    console.log(error.status);
                    console.log(error);
                    if (error.status === 401) {
                        this.router.navigate(['/login']);
                        this.mensagemService.aviso('Sessão inválida', 'Faça o login para acessar a aplicação');
                    } else {
                        this.mensagemService.mostrar(error);
                    }
                }
            ),
            finalize(() => {
                if (this.processosEmExecucao <= 0) {
                    LoadingComponent.hide();
                }
            })
        );
    }

    removerProcessoLoading() {
        if (this.processosEmExecucao > 0) {
            this.processosEmExecucao--;
        }
    }

}
