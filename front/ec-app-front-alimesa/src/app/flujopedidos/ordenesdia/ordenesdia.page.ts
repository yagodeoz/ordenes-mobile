import { Component, OnInit } from '@angular/core';
import {LoadingService} from '../../servicios/transversales/loading.service';
import {UtilmensajeService} from '../../servicios/transversales/utilmensaje.service';
import {Router} from '@angular/router';
import {ControlParametrosService} from '../../servicios/transversales/control-parametros.service';
import {ClientesService} from '../../servicios/ventas/clientes/clientes.service';
import { TasksServiceProvider } from '../../providers/tasks-service';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-ordenesdia',
  templateUrl: './ordenesdia.page.html',
  styleUrls: ['./ordenesdia.page.scss'],
})
export class OrdenesdiaPage implements OnInit {

  private postData = {
    id: '2',
    campos: {
      CODIGO_CLIENTE: 'P1.CODIGO_CLIENTE',
      IDENTIFICACION_FISCAL: 'P1.IDENTIFICACION_FISCAL',
      NOMBRE_CLIENTE: 'P1.NOMBRE_CLIENTE',
      SALESMAN: 'P1.SALESMAN',
      PriceList: 'P1.PriceList',
      Codigo_grupo_impuestos: 'P1.Codigo_grupo_impuestos',
      Codigo_Giro: 'P1.Codigo_Giro',
      DIRECCION_PRINCIPAL_1: 'P1.DIRECCION_PRINCIPAL_1',
      TELEFONO: 'P1.TELEFONO',
      E_MAIL: 'P1.E_MAIL',
      ORIGIN: 'P1.ORIGIN',
      DiasCobros: 'P1.DiasCobros',
      DiasEntregaFacturas: 'P1.DiasEntregaFacturas',
      LOCALIZACION_CLIENTE: 'P1.LOCALIZACION_CLIENTE'
    },
    filtro: {
      where: '',
      'order by': 'P1.CODIGO_CLIENTE desc',
      limit: ' 10'
    }
  };

  listaClientes: any;

  listaOrdenes : any;
  fechaHoy : any;

  constructor(private loadingService: LoadingService, private servicioCliente: ClientesService,
              private utilMensaje: UtilmensajeService, private router: Router,
              private controlParametros: ControlParametrosService, private tasksService: TasksServiceProvider, public alertController: AlertController) {

               }

  ngOnInit() {
    
  }

  ionViewDidEnter() {

   this.loadingService.loadingPresent('Cargando Órdenes...');
       var today = new Date();
       var dd = String(today.getDate()).padStart(2, '0');
       var mm = String(today.getMonth() + 1).padStart(2, '0'); 
       var yyyy = today.getFullYear();
       this.fechaHoy = dd+"/"+mm+"/"+yyyy;

       this.tasksService.listaOrdenesDia().then(res => {
          this.listaOrdenes = res;
          this.loadingService.loadingDismiss();
      });         
  }

 
 buscarCliente( patronNombre ){
   this.loadingService.loadingPresent('Cargando Órdenes...');

   this.tasksService.listaOrdenesDiaNombre(patronNombre).then(res => {
                   this.listaOrdenes = res;
                   this.loadingService.loadingDismiss();
    });

 }

 gestionarEliminacion (item) {

    this.alertController.create({
      header: 'Eliminación órden de pedido',
      subHeader: 'Número de órden referencial: ' + item.IDREFERENCIA,
      message: 'Está seguro de continuar?',
      buttons: [
        {
          text: 'Cancelar',
          handler: () => {
            console.log('eliminación de órden cancelada');
          }
        },
        {
          text: 'Continuar',
          handler: () => {
           this.eliminarOrden(item);
          }
        }
        
      ]
    }).then(res => {
      res.present();
    });
 }

 eliminarOrden(item) {

    this.loadingService.loadingPresent('Eliminando Órden...');

    this.tasksService.eliminarOrdenDia(item.IDREFERENCIA).then( resultado => {

      this.tasksService.eliminarDetalleOrdenesDiaReferencia(item.IDREFERENCIA).then( resp => {
           this.tasksService.listaOrdenesDia().then(res => {
                   this.listaOrdenes = res;
                   this.loadingService.loadingDismiss();
                    this.finalizar ();
                  });
      });
    });
 }


 finalizar () {

      this.alertController.create({
      header: 'Información',
      subHeader: 'Eliminación de órden de pedido, realizado correctamente',
      buttons: [
        {
          text: 'Finalizar',
          handler: () => {
              console.log("Listo");
          }
        }
        
      ]
    }).then(res => {
      res.present();
    });     

  }

 procesarOrden(item) {
    
    this.tasksService.clienteRegistroFiscal(item.IDENTIFICACIONCOMPRADOR).then( resultado => {
          if (resultado.length > 0){

             this.tasksService.listaOrdenesDiaReferencia(item.IDREFERENCIA).then( resultadoOrden => { 

                    if (resultadoOrden.length > 0) {

                      this.tasksService.listaDetalleOrdenesDiaReferencia(item.IDREFERENCIA).then( resultadoDetalle => { 

                        if (resultadoDetalle.length > 0) { 

                        this.tasksService.listaCategoriaProducto ().then(res => {
                          this.controlParametros.setParametro('cliente_item', resultado[0]);
                          this.controlParametros.setParametro('editar_orden', 'S');
                          this.controlParametros.setParametro('lista_categoria', res);
                           this.controlParametros.setParametro('cab_orden', resultadoOrden[0]);
                           this.controlParametros.setParametro("det_orden", resultadoDetalle);
                          this.router.navigate(['/cabecerapedido'] );
                        }, error => {
                        this.listaClientes = null;
                        console.log('Error al realizar la consulta ');
                        this.loadingService.loadingDismiss();
                        this.utilMensaje.presentarMensaje('Error al realizar la consulta de clientes');
                        });
                        }else {
                             this.utilMensaje.presentarMensaje('No existen detalles de ordenes con referencia : ' + item.IDREFERENCIA );  
                        }

                      });

                    } else {
                        this.utilMensaje.presentarMensaje('No existen ordenes con referencia : ' + item.IDREFERENCIA );  
                    }
              });
          }else {
            this.utilMensaje.presentarMensaje('No existen clientes con identificación : ' + item.IDENTIFICACIONCOMPRADOR );
          }
      
      });

    }

getDescripcionEstado(item)
{
  let descrip = "";

  if (item.ESTADOPROCESO === 'C') {
    descrip = "Órden Creada";
  }else
  {
    descrip = "Órden Replicada";
  }
  return descrip;
}


}
