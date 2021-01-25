import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppAsideModule, AppBreadcrumbModule, AppFooterModule, AppHeaderModule, AppSidebarModule} from '@coreui/angular';
import {ChartsModule} from 'ng2-charts/ng2-charts';
import {Daterangepicker} from 'ng2-daterangepicker';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {TabsModule} from 'ngx-bootstrap/tabs';
import {DeviceDetectorModule} from 'ngx-device-detector';
import {PERFECT_SCROLLBAR_CONFIG, PerfectScrollbarConfigInterface, PerfectScrollbarModule} from 'ngx-perfect-scrollbar';
import {NgxSpinnerModule} from 'ngx-spinner';
import {ToastrModule} from 'ngx-toastr';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app.routing';
import {LoadingComponent} from './componentes/loading/loading.component';
import {MensagemComponent} from './componentes/mensagem/mensagem.component';
import {VsmGridModule} from './componentes/vsm-grid/vsm-grid.module';
import {DefaultLayoutComponent} from './containers';
import {HomeComponent} from './views/home/home.component';
import {AuthInterceptor} from './rest/auth-interceptor';


const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
    suppressScrollX: true
};

const APP_CONTAINERS = [
    DefaultLayoutComponent
];


@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        AppAsideModule,
        AppBreadcrumbModule.forRoot(),
        AppFooterModule,
        AppHeaderModule,
        AppSidebarModule,
        PerfectScrollbarModule,
        BsDropdownModule.forRoot(),
        TabsModule.forRoot(),
        ChartsModule,
        VsmGridModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        ToastrModule.forRoot(),
        NgxSpinnerModule,
        Daterangepicker,
        DeviceDetectorModule.forRoot()
    ],
    declarations: [
        AppComponent,
        ...APP_CONTAINERS,
        LoadingComponent,
        MensagemComponent,
        HomeComponent,
    ],
    providers: [
        {
            provide: LocationStrategy,
            useClass: HashLocationStrategy
        },
        {
            provide: PERFECT_SCROLLBAR_CONFIG,
            useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor, multi: true
        }

    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
