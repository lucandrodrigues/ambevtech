import { Component, EventEmitter, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
    selector: 'app-loading',
    templateUrl: './loading.component.html',
    styleUrls: ['./loading.component.css']
})
export class LoadingComponent implements OnInit {

    private static showEvent = new EventEmitter<any>();
    private static hideEvent = new EventEmitter<any>();
    private open = false;

    constructor(private spinner: NgxSpinnerService) {
    }

    static show() {
        this.showEvent.emit();
    }

    static hide() {
        this.hideEvent.emit();
    }

    ngOnInit() {
        LoadingComponent.showEvent.subscribe(
            () => {
                this.showOrClose();
            },
            () => {
                this.showOrClose();
            }
        );

        LoadingComponent.hideEvent.subscribe(
            () => {
                this.close();
            },
            () => {
                this.close();
            }
        );
    }

    showOrClose() {
        this.open = !this.open;

        if (this.open) {
            this.spinner.show();
        } else {
            this.spinner.hide();
        }
    }

    close() {
        this.open = false;
        this.spinner.hide();
    }

}
