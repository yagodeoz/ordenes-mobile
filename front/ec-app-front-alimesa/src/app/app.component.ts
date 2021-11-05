import { Component, OnInit } from '@angular/core';

import { MenuController, Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { MessagesProvider } from './messages/messages';
import { ControlaccesologinService } from './servicios/login/controlaccesologin.service';
import { Router } from '@angular/router';
import { AuthenticationService } from './servicios/authentication.service';
import { SQLite } from '@ionic-native/sqlite/ngx';
import { TasksServiceProvider } from './providers/tasks-service';

@Component({
    selector: 'app-root',
    templateUrl: 'app.component.html',
    styleUrls: ['app.component.scss']
})
export class AppComponent implements OnInit {
    public selectedIndex = 0;
    public appPages: any;
    public labels = ['Link 1', 'Link 2', 'Link N'];

    public login: boolean;


    constructor(
        private platform: Platform,
        private splashScreen: SplashScreen,
        private statusBar: StatusBar,
        private msg: MessagesProvider,
        private controlAcceso: ControlaccesologinService,
        private router: Router,
        private auth: AuthenticationService,
        public menuCtrl: MenuController,
        public sqlite: SQLite,
        private tasksService: TasksServiceProvider
    ) {
        this.construccionMenu();
        this.initializeApp();
    }

    construccionMenu() {
        this.appPages = [
            this.getMenuHome(),
            //this.getMenuProfile()
        ];
        this.inclusionModulos();
    }



    private inclusionModulos() {
        this.agregarMenuSucursal();
        this.agregarMenuProductos();
        this.agregarMenuClientes();
        this.agregarMenuPedidos();
        this.agregarOrdenDia();
        this.agregarCobros();
        this.agregarCobrosdia();
        this.agregarflujoDespacho();
        this.agregarStock();
        this.agregarDespachodia();
        
    }

    initializeApp() {
        this.platform.ready().then(() => {
            this.login = false;
            this.statusBar.styleDefault();
            this.splashScreen.hide();

            this.sqlite.create({
                name: 'ordenespedido.db',
                location: 'default' // the location field is required
            }).then((db) => {
                this.tasksService.setDatabase(db);
                return this.tasksService.inicializarBaseDatos();
            })
                .then(() => {
                    this.splashScreen.hide();


                    this.auth.authState.subscribe(state => {
                        if (state) {
                            this.router.navigate(['/folder/PrincipalLogin']);
                        } else {
                            this.router.navigate(['login']);
                        }
                    });


                })
                .catch(error => {
                    console.error(error);
                });



        });
    }

    ngOnInit() {
        const path = window.location.pathname.split('folder/')[1];
        if (path !== undefined) {
            this.selectedIndex = this.appPages.findIndex(page => page.title.toLowerCase() === path.toLowerCase());
        }
    }

    private agregarflujoDespacho() {
        //validar roles y demas cosas
        this.appPages.push(this.getMenuFlujoDespacho());
    }

    private agregarOrdenDia() {
        //validar roles y demas cosas
        this.appPages.push(this.getMenuOrdenDia());
    }


    private agregarMenuProductos() {
        //validar roles y demas cosas
        this.appPages.push(this.getMenuProductos());
    }

    private agregarMenuSucursal() {
        //validar roles y demas cosas
        this.appPages.push(this.getMenuSucursal());
    }

    private agregarMenuClientes() {
        //validar roles y demas cosas
        this.appPages.push(this.getMenuClientes());
    }
    private agregarMenuPedidos() {
        //validar roles y demas cosas
        this.appPages.push(this.getMenuPedidos());
    }

    private agregarCobros() {
        //validar roles y demas cosas
        this.appPages.push(this.getMenuCobros());
    }

    private agregarStock(){
        //validar roles y demas cosas
        this.appPages.push(this.getMenuStock());
    }

    private agregarCobrosdia(){
        this.appPages.push(this.getMenuCobrosdia());
    }

    private agregarDespachodia(){
        this.appPages.push(this.getMenuDespachoDia());
    }

    private getMenuProductos() {
        return {
            title: this.msg.get('titulo_menu_productos'),
            url: '/listaproductos',  
            icon: 'grid'
        };
    }

    private getMenuOrdenDia() {
        return {
            title: this.msg.get('titulo_menu_orden_dia'),
            url: '/ordendia',
            icon: 'cart'
        };
    }

    private getMenuFlujoDespacho() {
        return {
            title: this.msg.get('titulo_menu_flujo_despacho'),
            url: '/flujodespacho',
            icon: 'copy'
        };
    }

    private getMenuClientes() {
        return {
            title: this.msg.get('titulo_menu_clientes'),
            url: '/listaclientes',
            icon: 'person'
        };
    }

    private getMenuPedidos() {
        return {
            title: this.msg.get('titulo_menu_pedidos'),
            url: '/pedidos',
            icon: 'newspaper'
        };
    }

    private getMenuCobros() {
        return {
            title: this.msg.get('titulo_menu_cobros'),
            url: '/cobros',
            icon: 'cash'
        };
    }

    private getMenuCobrosdia() {
        return {
            title: this.msg.get('titulo_menu_cobros_dia'),
            url: '/cobrosdia',
            icon: 'wallet'
        };
    }

    private getMenuHome() {
        return {
            title: this.msg.get('titulo_menu_home'),
            url: '/folder/PrincipalMenu',
            icon: 'home'
        };
    }

    private getMenuProfile() {
        return {
            title: this.msg.get('titulo_menu_perfil'),
            url: '/myprofile',
            icon: 'person'
        };
    }

    getMenuSucursal() {
        return {
            title: this.msg.get('titulo_menu_sucursal'),
            url: '/sucursal',
            icon: 'link'
        };
    }

    getMenuStock(){
        return {
            title: this.msg.get('titulo_menu_stock'),
            url: '/stock',
            icon: 'barcode'
        };
    }

    getMenuDespachoDia(){
        return {
            title: this.msg.get('titulo_menu_despachodia'),
            url: '/despachodia',
            icon: 'flag'
        };       
    }

    cerrarSesion() {
        this.menuCtrl.toggle();
        this.auth.logout();
    }





}
