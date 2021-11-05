import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {MessagesProvider} from '../messages/messages';
import { InAppBrowser } from '@ionic-native/in-app-browser/ngx';
import { LoadingService } from '../servicios/transversales/loading.service';

@Component({
    selector: 'app-folder',
    templateUrl: './folder.page.html',
    styleUrls: ['./folder.page.scss']
})
export class FolderPage implements OnInit {
    public folder: string;
    public mensajeBusqueda: string;
    public tituloApp: string;
    public direccionApp: string;
    public tituloCategorias: string;
    public botonMas: string;

    constructor(private activatedRoute: ActivatedRoute, private msg: MessagesProvider,
                private iab: InAppBrowser, private loadingService: LoadingService) {
        this.mensajeBusqueda = this.msg.get('input_buscar');
        this.tituloApp = this.msg.get('titulo_app');
        this.direccionApp = this.msg.get('direccion_app');
        this.tituloCategorias = this.msg.get('titulo_categorias');
        this.botonMas = this.msg.get('mas');
    }

    ngOnInit() {

        if (this.loadingService.existeEspiner())    
            this.loadingService.loadingDismiss();
        this.folder = this.activatedRoute.snapshot.paramMap.get('id');
    }

    OpenUrl() {
        const browser = this.iab.create('http://alimesa.com.ec/alimesa', '_blank');
        browser.show();
    }

}
