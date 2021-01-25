import { Component, EventEmitter, Input, Output, HostListener } from '@angular/core';
import { DeviceDetectorService } from 'ngx-device-detector';

@Component({
    selector: 'app-overlay',
    templateUrl: './overlay.component.html',
    styleUrls: ['./overlay.component.scss']
})
export class OverlayComponent {

    @Input() show = false;
    @Output() private showChange = new EventEmitter();

    // tslint:disable-next-line:no-output-on-prefix
    @Output() onClose: EventEmitter<any> = new EventEmitter();

    desktop: boolean;

    constructor(private service: DeviceDetectorService) {
        this.desktop = this.service.isDesktop();
    }

    @HostListener('window:keydown.esc')
    close() {
        this.show = false;
        this.showChange.emit(this.show);

        this.onClose.emit();
    }

}
