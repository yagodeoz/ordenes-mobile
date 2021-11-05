import { Component, OnInit } from '@angular/core';
import {ControlParametrosService} from '../../servicios/transversales/control-parametros.service';
import { ControlaccesologinService } from '../../servicios/login/controlaccesologin.service';
import {Router} from '@angular/router';
import { ModalController } from '@ionic/angular';
import {BuscarProductosDespachoPage} from '../buscarproductosdespacho/buscarproductosdespacho.page';
import { UtilmensajeService } from "../../servicios/transversales/utilmensaje.service";
import { AlertController } from '@ionic/angular';
import { TasksServiceProvider } from '../../providers/tasks-service';
import { LoadingService } from '../../servicios/transversales/loading.service';
import { DOCUMENT } from '@angular/common'; 



@Component({
  selector: 'app-cabecera-despacho',
  templateUrl: './cabeceradespachos.page.html',
  styleUrls: ['./cabeceradespachos.page.scss'],
})
export class CabeceradespachoPage implements OnInit {


  item: any;

  cabOrden : any;

  detOrden : any = [];

  listaCategoria : any;

  fechaEmision : String;

  fechaminima : any;
  fechamaxima : any;

  constructor(private controlParametros: ControlParametrosService, private router: Router, public viewCtrl: ModalController, 
   private controlAcceso: ControlaccesologinService, private utilMensaje: UtilmensajeService,  public alertController: AlertController, 
   private tasksService: TasksServiceProvider,  private loadingService: LoadingService
              ) {
                
                  var today = new Date();
                  var dd = String(today.getDate()).padStart(2, '0');
                  var mm = String(today.getMonth() + 1).padStart(2, '0'); 
                  var yyyy = today.getFullYear();
                  this.fechaminima = yyyy + "-" + mm + "-" + dd;
                  this.fechamaxima = (yyyy + 1) + "-" + mm + "-" + dd;  

                this.listaCategoria =   this.controlParametros.getParametro('lista_categoria');
                this.cabOrden =   <any> this.controlParametros.getParametro('cab_despacho');
                this.detOrden =  <any []>this.controlParametros.getParametro('det_despacho');
                this.cabOrden.fecha_registro  =  this.getFechaActual();

                this.controlParametros.setParametro('cab_despacho', this.cabOrden);
                this.controlParametros.setParametro("det_despacho", this.detOrden);


               }

  ngOnInit() { 
   
  }


 getFechaActual()
  {
    var today = new Date();
                  var dd = String(today.getDate()).padStart(2, '0');
                  var mm = String(today.getMonth() + 1).padStart(2, '0'); 
                  var yyyy = today.getFullYear();
                  return mm + '/' + dd + '/' + yyyy;
  }

 ionViewDidEnter() {

     this.detOrden.forEach(element => {
                      let inputValue = <HTMLInputElement> document.getElementById(element.codigoprincipal);   
                      inputValue.value = element.cantidad;
                  });
                  
  }

  registrarOrden() {

    if (this.detOrden.length < 1 )
    {
       this.utilMensaje.presentarMensaje('La orden debe tener al menos un detalle');
      return;
    }

   this.alertController.create({
      header: 'Generar Despacho',
      subHeader: 'Órden de pedido: Total $ ' + this.cabOrden.importetotal,
      message: 'Está seguro de realizar el despacho?',
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
           this.salvarOrdenPedido().then(() => {
              this.router.navigateByUrl('/folder/PrincipalLogin');
           });
          }
        }
        
      ]
    }).then(res => {
      res.present();
    });

  }
  
  

  salvarOrdenPedido(){

      this.loadingService.loadingPresent('Procesando, por favor espere...');

      return new Promise((resolve, reject) => {
      
      let eliminarCabOrden: Array<string> = [];    

      eliminarCabOrden.push('DELETE FROM ' + this.tasksService.TABLA_CABDESPACHO + ' WHERE numerooden =' +  this.cabOrden.numerooden);

      this.tasksService.db.sqlBatch(eliminarCabOrden)
          .then(() => {
              let eliminarDetOrden : Array<string> = [];    
              eliminarDetOrden.push('DELETE FROM ' + this.tasksService.TABLA_DETALLESDESPACHO + ' WHERE numerooden =' +  this.cabOrden.numerooden);
               this.tasksService.db.sqlBatch(eliminarDetOrden)
               .then(() => {  

              let sqlGeneral = 'INSERT OR REPLACE INTO [TABLA]([CAMPOS]) VALUES([VALORES]);';
              let sqlInsert: Array<string> = [];

              let sqlCabecera = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_CABDESPACHO);
              sqlCabecera = sqlCabecera.replace('[CAMPOS]', "id, estado, idreferencia, campo_auditoria, fecha_actualizacion, fecha_registro, observacion, " +
                                        "direccioncomprador, estadoproceso, fechaemision, idcliente, idsucursal, idusuario, identificacioncomprador, importetotal, moneda, " +
                                        "nombrevendedor, numerofactura, numerooden, razonsocialcomprador, subtotal0, subtotal12, totaldescuento, totalimpuesto, totalsinimpuestos, canalcreacion, " +
                                        "codigocliente, codigosucursal, diaspago, placavehiculo, usuarioasignado, usuarioasignante, descripcionlistaprecio, tipopago"
                                      );
              
          let referencia = new Date().getTime();
          sqlInsert.push(sqlCabecera.replace('[VALORES]', 
            "\"" + this.cabOrden.id + "\"," +
            "\"" + this.cabOrden.estado + "\"," +
            "\"" + referencia + "\"," +
            "\"" + this.cabOrden.campo_auditoria + "\"," +
            "\"" + this.cabOrden.fecha_actualizacion + "\"," +
            "\"" + this.cabOrden.fecha_registro + "\"," + 
            "\"" + this.cabOrden.observacion + "\"," +
            "\"" + this.cabOrden.direccioncomprador + "\"," +
            "\"DESPACHADOLOCAL\"," +
            "\"" + this.cabOrden.fechaemision + "\"," +
            "\"" + this.cabOrden.idcliente + "\"," +
            "\"0\"," +
            "\"0\"," +
            "\"" + this.cabOrden.identificacioncomprador + "\"," +
            "\"" + this.cabOrden.importetotal + "\"," +
            "\"" + this.cabOrden.moneda + "\"," +
            "\"" + this.cabOrden.nombrevendedor + "\"," +
            "\"" + this.cabOrden.numerofactura + "\"," +
            "\"" + this.cabOrden.numerooden + "\"," +
            "\"" + this.cabOrden.razonsocialcomprador + "\"," +
            "\"" + this.cabOrden.subtotal0 + "\"," +
            "\"0\"," +
            "\"" + this.cabOrden.totaldescuento + "\"," +
            "\"" + this.cabOrden.totalimpuesto + "\"," +
            "\"" + this.cabOrden.totalsinimpuestos + "\"," +
            "\"" + this.cabOrden.canalcreacion + "\"," +
            "\"" + this.cabOrden.codigocliente + "\"," +
            "\"" + this.cabOrden.codigosucursal + "\"," +
            "\"" + this.cabOrden.diaspago + "\"," +
            "\"" + this.cabOrden.placavehiculo + "\"," +
            "\"" + this.cabOrden.usuarioasignado + "\"," +
            "\"" + this.cabOrden.usuarioasignante + "\"," +
            "\"" + this.cabOrden.descripcionlistaprecio + "\"," +
            "\"" + this.cabOrden.tipopago+ "\"")); 

        this.tasksService.db.sqlBatch(sqlInsert)
          .then(() => {
             
             let sqlInsert: Array<string> = [];
             let sqlDetalle = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_DETALLESDESPACHO);
             sqlDetalle = sqlDetalle.replace('[CAMPOS]', "id, estado, idreferencia, campo_auditoria, fecha_actualizacion, fecha_registro, observacion, " +
                                            "cantidad, codigoauxiliar, codigoprincipal, descripcion, descuento, detallesadicionales, preciototalconimpuesto, preciototalsinimpuesto, preciounitario, " +
                                            "id_cab_despacho, id_producto, codigoproducto, numerofactura, numerooden, preciopedido"
                                          );
             
              this.detOrden.forEach(element => {

              sqlInsert.push(sqlDetalle.replace('[VALORES]', 
                "\"" + element.id + "\"," +
                "\"" + element.estado + "\"," +
                "\"" + referencia + "\"," +
                "\"" + element.campo_auditoria + "\"," +
                "\"" + element.fecha_actualizacion + "\"," +
                "\"" + element.fecha_registro + "\"," +
                "\"" + element.observacion + "\"," +
                "\"" + element.cantidad + "\"," +
                "\"" + element.codigoauxiliar + "\"," +
                "\"" + element.codigoprincipal + "\"," +
                "\"" + element.descripcion + "\"," +
                "\"0\"," +
                "\"" + element.detallesadicionales + "\"," +
                "\"" + element.preciototalconimpuesto + "\"," +
                "\"" + element.preciototalsinimpuesto + "\"," +
                "\"" + element.preciounitario + "\"," +
                "\"" + this.cabOrden.id + "\"," +
                "\"0\"," +
                "\"" + element.codigoproducto + "\"," +
                "\"" + element.numerofactura + "\"," +
                "\"" + element.numerooden + "\"," +
                "\"" + element.preciopedido + "\""));

            });
             this.tasksService.db.sqlBatch(sqlInsert)
          .then(() => {

             //bajar el stock 
             let sqlUpdate: Array<string> = [];
             this.detOrden.forEach(element => {

                let updateStock =  " UPDATE " + this.tasksService.TABLA_STOCK + " SET saldo = "  + (element.saldostock - element.cantidad ) 
                                  + " WHERE codigoproducto = '" + element.codigoprincipal + "'";
                sqlUpdate.push(updateStock);
             });

             this.tasksService.db.sqlBatch(sqlUpdate)
                  .then(() => {    
                      console.log("Registrado correctamente");
                      this.controlParametros.removerParametro('cab_despacho');
                      this.controlParametros.removerParametro("det_despacho");
                      resolve(true); 
                  });
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
      component: BuscarProductosDespachoPage,
      cssClass: 'my-custom-modal-css'

    });

     modal.onDidDismiss().then((dataReturned) => {
       


        this.cabOrden =   <any> this.controlParametros.getParametro('cab_despacho');
        this.detOrden =  <any []>this.controlParametros.getParametro('det_despacho'); 

        this.detOrden.forEach(element => {
            let inputValue = <HTMLInputElement> document.getElementById(element.codigoprincipal);   
            inputValue.value = element.cantidad;
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

  calcularDatos (event, detalle) {

    if (!event)
       this.utilMensaje.presentarMensaje('El detalle debe tener una cantidad mínima');
    if (event <= 0)
      this.utilMensaje.presentarMensaje('El detalle debe tener una cantidad mínima');

    if (event > detalle.saldostock) {
      this.utilMensaje.presentarMensaje('La cantidad del producto: ' + detalle.codigoprincipal + ' supera el saldo del stock');
    }

    let totalSinImpuesto = 0;
    let totalImporte = 0;

    this.detOrden.forEach(element => {

            if (element.estado === 'A') {
               if (!element.cantidad)
                  element.cantidad = 1;

              if (element.cantidad <= 0 )
                element.cantidad = 1;  

              if (element.cantidad > element.saldostock)
                element.cantidad = element.saldostock;

              element.preciototalconimpuesto = element.preciounitario * element.cantidad;
              element.preciototalsinimpuesto = element.preciounitario * element.cantidad
              totalSinImpuesto = totalSinImpuesto + element.preciototalsinimpuesto;
              totalImporte = totalImporte + element.preciototalconimpuesto; 
            }

            
    });

    this.cabOrden.totalsinimpuestos=totalSinImpuesto;
    this.cabOrden.importetotal=totalImporte;

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

            if (element.estado === 'A') {
              if (!element.cantidad)
                  element.cantidad = 1;

              if (element.cantidad <= 0 )
                element.cantidad = 1;  

              if (element.cantidad > element.saldostock)
                element.cantidad = element.saldostock;

              element.preciototalconimpuesto = element.preciounitario * element.cantidad;
              element.preciototalsinimpuesto = element.preciounitario * element.cantidad;
              totalSinImpuesto = totalSinImpuesto + element.preciototalsinimpuesto;
              totalImporte = totalImporte + element.preciototalconimpuesto; 
            }
  
    });

    this.cabOrden.totalsinimpuestos=totalSinImpuesto;
    this.cabOrden.importetotal=totalImporte;
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
