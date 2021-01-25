import { AfterViewInit, Directive, ElementRef, EventEmitter, Input, OnDestroy, Output, Renderer2 } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs';

declare var $: any;

@Directive({
    selector: '[appFormValidator]'
})
export class FormValidatorDirective implements AfterViewInit, OnDestroy {

    @Input() formGroupValidator: FormGroup;
    @Input() disableSubmitButton = true;

    @Output() isValidForm = new EventEmitter<any>();

    private listFieldError = new Set();

    private subscriptionList: Subscription[] = [];


    constructor(
        private el: ElementRef,
        private renderer: Renderer2) {
    }

    ngAfterViewInit() {
        this.el.nativeElement.querySelectorAll('input').forEach(input => {
            this.checkField(input);
        });

        this.el.nativeElement.querySelectorAll('select').forEach(input => {
            this.checkField(input);
        });

        this.el.nativeElement.querySelectorAll('textarea').forEach(input => {
            this.checkField(input);
        });
    }

    ngOnDestroy() {
        if (this.subscriptionList) {
            this.subscriptionList.forEach(item => {
                item.unsubscribe();
            });
        }
    }

    checkField(input) {
        if (input) {
            const value = input.attributes.getNamedItem('formControlName') ?
                input.attributes.getNamedItem('formControlName').value : null;

            if (value && this.formGroupValidator.get(value)) {
                this.validateField(input, value);

                this.subscriptionList.push(
                    this.formGroupValidator.get(value).valueChanges.subscribe(
                        () => {
                            this.validateField(input, value);
                        }
                    )
                );
            }
        }
    }

    validateField(input, value) {
        const errors = this.formGroupValidator.get(value).errors;

        if (errors) {
            this.listFieldError.add(value);

            this.renderer.addClass(input, 'is-invalid');

            this.renderer.setAttribute(
                input,
                'title',
                'Informação inválida/obrigatória.'
            );
        } else {
            this.listFieldError.delete(value);

            this.renderer.removeClass(input, 'is-invalid');
            this.renderer.removeAttribute(input, 'title');

            if (this.formGroupValidator.get(value).value) {
                this.renderer.addClass(input, 'is-valid');
            } else {
                this.renderer.removeClass(input, 'is-valid');
            }
        }

        this.checkSubmitButton();
        this.isValidForm.emit(this.listFieldError.size <= 0);
    }

    checkSubmitButton() {
        if (this.disableSubmitButton) {
            this.el.nativeElement.querySelectorAll('button').forEach(button => {
                if (button.attributes.getNamedItem('type') && button.attributes.getNamedItem('type').value === 'submit') {
                    if (this.listFieldError.size > 0) {
                        this.renderer.setAttribute(button, 'disabled', 'disabled');

                        this.renderer.setAttribute(
                            button,
                            'title',
                            'Formulário possuí campos inválidos/não informados.'
                        );
                    } else {
                        this.renderer.removeAttribute(button, 'disabled');
                        this.renderer.removeAttribute(button, 'title');
                    }
                }
            });
        }
    }

}
