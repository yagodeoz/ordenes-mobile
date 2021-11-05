import { Component, OnInit } from '@angular/core';
import {LoadingService} from '../../servicios/transversales/loading.service';
import {UtilmensajeService} from '../../servicios/transversales/utilmensaje.service';
import {Router} from '@angular/router';
import {ControlParametrosService} from '../../servicios/transversales/control-parametros.service';
import {ClientesService} from '../../servicios/ventas/clientes/clientes.service';
import { TasksServiceProvider } from '../../providers/tasks-service';

@Component({
  selector: 'app-despachodia',
  templateUrl: './despachodia.page.html',
  styleUrls: ['./despachodia.page.scss'],
})
export class DespachodiaPage implements OnInit {

  listaDespachos : any;
  fechaHoy : any;

  constructor(private loadingService: LoadingService, private servicioCliente: ClientesService,
              private utilMensaje: UtilmensajeService, private router: Router,
              private controlParametros: ControlParametrosService,
              private tasksService: TasksServiceProvider) { }

  
  ngOnInit() {
  
  }


ionViewDidEnter() {

   this.loadingService.loadingPresent('Cargando Órdenes...');
       var today = new Date();
       var dd = String(today.getDate()).padStart(2, '0');
       var mm = String(today.getMonth() + 1).padStart(2, '0'); 
       var yyyy = today.getFullYear();
       this.fechaHoy = dd+"/"+mm+"/"+yyyy;
       this.tasksService.obtenerListaDespachosRealizados().then(res => {
          this.listaDespachos = res;
          this.loadingService.loadingDismiss();
      });         
  }
  

  crearCliente() {
    this.router.navigate(['/crearcliente'] );
  }

  
  procesarCliente(item) {
  
  }

   buscarCliente( patronNombre ){
   this.loadingService.loadingPresent('Cargando Despachos...');

   this.tasksService.listaDespachoNombre(patronNombre).then(res => {
                   this.listaDespachos = res;
                   this.loadingService.loadingDismiss();
    });

 }

 procesarDespacho(item) {   
   
   this.tasksService.listaDetalleDespacho(item).then( resultadoDetalle => { 
            if (resultadoDetalle.length > 0) { 
                  this.controlParametros.setParametro('cab_despacho', item);
                  this.controlParametros.setParametro("det_despacho", resultadoDetalle);
                  this.router.navigate(['/detalledespacho'] );                  
              }else {
                  this.utilMensaje.presentarMensaje('No existen detalles para el despacho : ' + item.numerooden );  
              }
        });
    }
}
