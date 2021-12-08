import { Component, OnInit } from '@angular/core';
import {ControlParametrosService} from '../../servicios/transversales/control-parametros.service';
import { ControlaccesologinService } from '../../servicios/login/controlaccesologin.service';
import {Router} from '@angular/router';
import { ModalController } from '@ionic/angular';
import {BuecarproductospedidoPage} from '../buecarproductospedido/buecarproductospedido.page';
import { UtilmensajeService } from "../../servicios/transversales/utilmensaje.service";
import { AlertController } from '@ionic/angular';
import { TasksServiceProvider } from '../../providers/tasks-service';
import { LoadingService } from '../../servicios/transversales/loading.service';
import { DOCUMENT } from '@angular/common'; 



@Component({
  selector: 'app-cabecera-pedidos',
  templateUrl: './cabecerapedidos.page.html',
  styleUrls: ['./cabecerapedidos.page.scss'],
})
export class CabecerapedidosPage implements OnInit {


  item: any;

  cabOrden : any;

  detOrden : any = [];

  listaCategoria : any;

  editarOrden : boolean;

  fechaEmision : String;

  constructor(private controlParametros: ControlParametrosService, private router: Router, public viewCtrl: ModalController, 
   private controlAcceso: ControlaccesologinService, private utilMensaje: UtilmensajeService,  public alertController: AlertController, 
   private tasksService: TasksServiceProvider,  private loadingService: LoadingService
              ) {
                
                this.item = this.controlParametros.getParametro('cliente_item');
                this.listaCategoria =   this.controlParametros.getParametro('lista_categoria');

                this.editarOrden = this.controlParametros.getParametro('editar_orden') === 'S';
                if (this.editarOrden) {
                  this.cabOrden =   <any> this.controlParametros.getParametro('cab_orden');
                  this.detOrden =  <any []>this.controlParametros.getParametro('det_orden');
                } else {

                  var today = new Date();
                  var dd = String(today.getDate()).padStart(2, '0');
                  var mm = String(today.getMonth() + 1).padStart(2, '0'); 
                  var yyyy = today.getFullYear();

                  this.cabOrden = {
                            ID : null,
                            ESTADO : 'A',
                            IDREFERENCIA : new Date().getTime(),
                            CAMPO_AUDITORIA : this.controlAcceso.getDataLogin().username,
                            FECHAACTUALIZACION : mm + '/' + dd + '/' + yyyy,
                            FECHAREGISTRO : mm + '/' + dd + '/' + yyyy,
                            OBSERVACION : 'REG-APP-MOBILE',
                            DIRECCIONCOMPRADOR : this.item.DIRECCION,
                            ESTADOPROCESO : 'C',
                            FECHAEMISION : mm + '/' + dd + '/' + yyyy,
                            IDCLIENTE : this.item.ID,
                            IDSUCURSAL : 0,
                            IDUSUARIO : 0,
                            IDENTIFICACIONCOMPRADOR : this.item.IDENTIFICACION,
                            IMPORTETOTAL : 0,
                            MONEDA : 'DOL',
                            NOMBREVENDEDOR : this.controlAcceso.getDataLogin().nombre,
                            NUMEROODEN : null,
                            RAZONSOCIALCOMPRADOR : this.item.NOMBRECOMPLETO,
                            SUBTOTAL0 : 0,
                            SUBTOTAL12 : 0,
                            TOTALDESCUENTO : 0,
                            TOTALIMPUESTO : 0,
                            TOTALSINIMPUESTOS : 0,
                            CANALCREACION : 'MOB',
                            CODIGOCLIENTE : this.item.CODCLIENTE,
                            CODIGOSUCURSAL : null,
                            USUARIOASIGNADO : this.controlAcceso.getDataLogin().username,
                            USUARIOASIGNANTE : this.controlAcceso.getDataLogin().username,
                  }
                  
                  
                  this.cabOrden.TOTALSINIMPUESTOS=0;
                  this.cabOrden.TOTALDESCUENTO=0;
                  this.cabOrden.TOTALIMPUESTO=0;
                  this.cabOrden.IMPORTETOTAL=0;
                  this.detOrden = [];
                } 

                this.controlParametros.setParametro('cab_orden', this.cabOrden);
                this.controlParametros.setParametro("det_orden", this.detOrden);

               }

  ngOnInit() { 
   
  }

 ionViewDidEnter() {

     this.detOrden.forEach(element => {
                      let inputValue = <HTMLInputElement> document.getElementById(element.CODIGOPRINCIPAL);   
                      inputValue.value = element.CANTIDAD;
                  });
                  
  }

  registrarOrden() {

    if (this.detOrden.length < 1 )
    {
       this.utilMensaje.presentarMensaje('La orden debe tener al menos un detalle');
      return;
    }

   this.alertController.create({
      header: 'Creación órden de pedido',
      subHeader: 'Órden de pedido: Total $ ' + this.cabOrden.IMPORTETOTAL,
      message: 'Está seguro de continuar?',
      buttons: [
        {
          text: 'Cancelar',
          handler: () => {
            console.log('órden cancelada');
          }
        },
        {
          text: 'Continuar',
          handler: () => {
           this.salvarOrdenPedido(this.cabOrden).then(() => {
                if (this.loadingService.existeEspiner())    
                  this.loadingService.loadingDismiss();
                this.finalizar ();           
           });
          }
        }
        
      ]
    }).then(res => {
      res.present();
    });

  }
  
  finalizar () {

      this.alertController.create({
      header: 'Información',
      subHeader: 'Órden de pedido, Creado correctamente',
      buttons: [
        {
          text: 'Finalizar',
          handler: () => {
              this.router.navigateByUrl('/ordendia');
          }
        }
        
      ]
    }).then(res => {
      res.present();
    });     

  }

  salvarOrdenPedido(element){

      this.loadingService.loadingPresent('Procesando, por favor espere...');

      return new Promise((resolve, reject) => {
      
      let eliminarCabOrden: Array<string> = [];    

      eliminarCabOrden.push('DELETE FROM ' + this.tasksService.TABLA_CABORDEN + ' WHERE IDREFERENCIA =' +  element.IDREFERENCIA);

      this.tasksService.db.sqlBatch(eliminarCabOrden)
          .then(() => {
              //----
              let eliminarDetOrden : Array<string> = [];    
              eliminarDetOrden.push('DELETE FROM ' + this.tasksService.TABLA_DETALLESORDEN + ' WHERE IDREFERENCIA =' +  element.IDREFERENCIA);
               this.tasksService.db.sqlBatch(eliminarDetOrden)
               .then(() => {  

              let sqlGeneral = 'INSERT OR REPLACE INTO [TABLA]([CAMPOS]) VALUES([VALORES]);';
              let sqlInsert: Array<string> = [];

              let sqlCabecera = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_CABORDEN);
                sqlCabecera = sqlCabecera.replace('[CAMPOS]', "ESTADO, IDREFERENCIA, CAMPO_AUDITORIA, FECHAACTUALIZACION, FECHAREGISTRO, OBSERVACION, " +
                                  "DIRECCIONCOMPRADOR, ESTADOPROCESO, FECHAEMISION, IDCLIENTE, IDSUCURSAL, IDUSUARIO, IDENTIFICACIONCOMPRADOR, " +
                                  "IMPORTETOTAL, MONEDA, NOMBREVENDEDOR, NUMEROODEN, RAZONSOCIALCOMPRADOR, SUBTOTAL0, SUBTOTAL12, TOTALDESCUENTO, " +
                                  "TOTALIMPUESTO, TOTALSINIMPUESTOS, CANALCREACION, CODIGOCLIENTE,  CODIGOSUCURSAL, USUARIOASIGNADO, USUARIOASIGNANTE ");
              
          sqlInsert.push(sqlCabecera.replace('[VALORES]', 
            "\"" + element.ESTADO + "\"," +
            "\"" + element.IDREFERENCIA + "\"," +
            "\"" + element.CAMPO_AUDITORIA + "\"," +
            "\"" + element.FECHAACTUALIZACION + "\"," +
            "\"" + element.FECHAREGISTRO + "\"," + 
            "\"" + element.OBSERVACION + "\"," +
            "\"" + element.DIRECCIONCOMPRADOR + "\"," +
            "\"" + element.ESTADOPROCESO + "\"," +
            "\"" + element.FECHAEMISION + "\"," +
            "\"" + element.IDCLIENTE + "\"," +
            "\"" + element.IDSUCURSAL + "\"," +
            "\"" + element.IDUSUARIO + "\"," +
            "\"" + element.IDENTIFICACIONCOMPRADOR + "\"," +
            "\"" + element.IMPORTETOTAL + "\"," +
            "\"" + element.MONEDA + "\"," +
            "\"" + element.NOMBREVENDEDOR + "\"," +
            "\"" + element.NUMEROODEN + "\"," +
            "\"" + element.RAZONSOCIALCOMPRADOR + "\"," +
            "\"" + element.SUBTOTAL0 + "\"," +
            "\"" + element.SUBTOTAL12 + "\"," +
            "\"" + element.TOTALDESCUENTO + "\"," +
            "\"" + element.TOTALIMPUESTO + "\"," +
            "\"" + element.TOTALSINIMPUESTOS + "\"," +
            "\"" + element.CANALCREACION + "\"," +
            "\"" + element.CODIGOCLIENTE + "\"," +
            "\"" + element.CODIGOSUCURSAL + "\"," +
            "\"" + element.USUARIOASIGNADO + "\"," +
            "\"" + element.USUARIOASIGNANTE+ "\"")); 

        this.tasksService.db.sqlBatch(sqlInsert)
          .then(() => {
             
             let sqlInsert: Array<string> = [];
             let sqlDetalle = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_DETALLESORDEN);
             sqlDetalle = sqlDetalle.replace('[CAMPOS]', "ESTADO, IDREFERENCIA, FECHAACTUALIZACION, FECHAREGISTRO, OBSERVACION, " +
                          "CANTIDAD, CODIGOAUXILIAR, CODIGOPRINCIPAL, DESCRIPCION, DESCUENTO, DETALLESADICIONALES, PRECIOTOTALCONIMPUESTO, " +
                          "PRECIOTOTALSINIMPUESTO, PRECIOUNITARIO, ID_CABECERA, ID_PRODUCTO, NUMEROORDEN ");
             
             
             
              this.detOrden.forEach(element => {

              sqlInsert.push(sqlDetalle.replace('[VALORES]', 
                "\"" + element.ESTADO + "\"," +
                "\"" + element.IDREFERENCIA + "\"," +
                "\"" + element.FECHAACTUALIZACION + "\"," +
                "\"" + element.FECHAREGISTRO + "\"," +
                "\"" + element.OBSERVACION + "\"," +
                "\"" + element.CANTIDAD + "\"," +
                "\"" + element.CODIGOAUXILIAR + "\"," +
                "\"" + element.CODIGOPRINCIPAL + "\"," +
                "\"" + element.DESCRIPCION + "\"," +
                "\"" + element.DESCUENTO + "\"," +
                "\"" + element.DETALLESADICIONALES + "\"," +
                "\"" + element.PRECIOTOTALCONIMPUESTO + "\"," +
                "\"" + element.PRECIOTOTALSINIMPUESTO + "\"," +
                "\"" + element.PRECIOUNITARIO + "\"," +
                "\"" + element.ID_CABECERA + "\"," +
                "\"" + element.ID_PRODUCTO + "\"," +
                "\"" + element.NUMEROORDEN + "\""));

            });

             this.tasksService.db.sqlBatch(sqlInsert)
          .then(() => {

                 
             console.log("Registrado correctamente");
             this.controlParametros.removerParametro('cab_orden');
             this.controlParametros.removerParametro("det_orden");

             resolve(true); 
           

          });
             
          });
      });  


              //--
          });


});
      
      

  }


  editarCliente() {

    this.router.navigate(['/editcliente'] );
  }

  levantarBusqueda() {
    this.openModal();
  }

  async openModal() {
    const modal = await this.viewCtrl.create({
      component: BuecarproductospedidoPage,
      cssClass: 'my-custom-modal-css'

    });

     modal.onDidDismiss().then((dataReturned) => {
       


        this.cabOrden =   <any> this.controlParametros.getParametro('cab_orden');
        this.detOrden =  <any []>this.controlParametros.getParametro('det_orden'); 

        this.detOrden.forEach(element => {
            let inputValue = <HTMLInputElement> document.getElementById(element.CODIGOPRINCIPAL);   
            inputValue.value = element.CANTIDAD;
        });

    });


    return await modal.present();
  }


  registrarCantidad(event: any) {
    const pattern = /[0-9.,]/;
    let inputChar = String.fromCharCode(event.charCode);

    if (!pattern.test(inputChar)) {
      return;
    }  

    this.detOrden.forEach(element => {
       if (!element.CANTIDAD){
         element.CANTIDAD = 1;
       }
    });

  }

  calcularDatos (event: any) {

    if (!event)
       this.utilMensaje.presentarMensaje('El detalle debe tener una cantidad mínima');
    if (event <= 0)
      this.utilMensaje.presentarMensaje('El detalle debe tener una cantidad mínima');

    let totalSinImpuesto = 0;
    let totalImporte = 0;

    this.detOrden.forEach(element => {

            if (element.ESTADO === 'A') {
               if (!element.CANTIDAD)
                  element.CANTIDAD = 1;

              if (element.CANTIDAD <= 0 )
                element.CANTIDAD = 1;  

              element.PRECIOTOTALCONIMPUESTO = element.PRECIOUNITARIO * element.CANTIDAD;
              element.PRECIOTOTALSINIMPUESTO = element.PRECIOUNITARIO * element.CANTIDAD
              totalSinImpuesto = totalSinImpuesto + element.PRECIOTOTALSINIMPUESTO;
              totalImporte = totalImporte + element.PRECIOTOTALCONIMPUESTO; 
            }

            
    });

    this.cabOrden.TOTALSINIMPUESTOS=totalSinImpuesto;
    this.cabOrden.IMPORTETOTAL=totalImporte;

  }

  eliminar(det){
    console.log(det);
    const index = this.detOrden.indexOf(det, 0);
    if (index > -1) {
        this.detOrden.splice(index, 1);
    }

    let totalSinImpuesto = 0;
    let totalImporte = 0;

    this.detOrden.forEach(element => {

            if (element.ESTADO === 'A') {
               if (!element.CANTIDAD)
                  element.CANTIDAD = 1;

              if (element.CANTIDAD <= 0 )
                element.CANTIDAD = 1;  

              element.PRECIOTOTALCONIMPUESTO = element.PRECIOUNITARIO * element.CANTIDAD;
              element.PRECIOTOTALSINIMPUESTO = element.PRECIOUNITARIO * element.CANTIDAD
              totalSinImpuesto = totalSinImpuesto + element.PRECIOTOTALSINIMPUESTO;
              totalImporte = totalImporte + element.PRECIOTOTALCONIMPUESTO; 
            }
  
    });

    this.cabOrden.TOTALSINIMPUESTOS=totalSinImpuesto;
    this.cabOrden.IMPORTETOTAL=totalImporte;

  }
  

}

class DetalleOrden {

   ID : number;
   ESTADO : String;
   IDREFERENCIA : String;
   FECHAACTUALIZACION : String;
   FECHAREGISTRO : String;
   OBSERVACION : String;
   CANTIDAD : number;
   CODIGOAUXILIAR : String;
   CODIGOPRINCIPAL : String;
   DESCRIPCION : String;
   DESCUENTO : number;
   DETALLESADICIONALES : String;
   PRECIOTOTALCONIMPUESTO : number;
   PRECIOTOTALSINIMPUESTO : number;
   PRECIOUNITARIO : number;
   ID_CABECERA : number;
   ID_PRODUCTO : number;
   NUMEROORDEN : String;
}   


class CabOrdenPedido {
    ID : number;
    ESTADO : String;
    IDREFERENCIA : String;
    CAMPO_AUDITORIA : String;
    FECHAACTUALIZACION : String;
    FECHAREGISTRO : String;
    OBSERVACION : String;
    DIRECCIONCOMPRADOR : String;
    ESTADOPROCESO : String;
    FECHAEMISION : String;
    IDCLIENTE : String;
    IDSUCURSAL : String;
    IDUSUARIO : String;
    IDENTIFICACIONCOMPRADOR : String;
    IMPORTETOTAL : number;
    MONEDA : String;
    NOMBREVENDEDOR : String;
    NUMEROODEN : String;
    RAZONSOCIALCOMPRADOR : String;
    SUBTOTAL0 : number;
    SUBTOTAL12 : number;
    TOTALDESCUENTO : number;
    TOTALIMPUESTO : number;
    TOTALSINIMPUESTOS : number;
    CANALCREACION : String;
    CODIGOCLIENTE : String;
    CODIGOSUCURSAL : String;
    USUARIOASIGNADO : String;
    USUARIOASIGNANTE : String;
}
