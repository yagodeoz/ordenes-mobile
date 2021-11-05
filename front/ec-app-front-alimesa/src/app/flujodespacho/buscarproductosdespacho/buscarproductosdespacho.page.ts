import { Component, OnInit } from '@angular/core';
import {LoadingService} from '../../servicios/transversales/loading.service';
import {ProductosService} from '../../servicios/ventas/productos/productos.service';
import {UtilmensajeService} from '../../servicios/transversales/utilmensaje.service';
import {Router} from '@angular/router';
import {ControlParametrosService} from '../../servicios/transversales/control-parametros.service';
import {ModalController} from '@ionic/angular';
import { TasksServiceProvider } from '../../providers/tasks-service';
import { ControlaccesologinService } from '../../servicios/login/controlaccesologin.service';

@Component({
  selector: 'app-buscarproductosdespacho',
  templateUrl: './buscarproductosdespacho.page.html',
  styleUrls: ['./buscarproductosdespacho.page.scss'],
})
export class BuscarProductosDespachoPage implements OnInit {

  private postData = {
    id: '1',
    campos: {
      idProCorp: 'P1.PRODUCT_ID_CORP',
      idPro: 'P1.PRODUCT_ID',
      nombreProd: 'P1.PRODUCT_NAME',
      costoRep1: 'P1.COSTO_REPOSICION_1',
      costoRep2: 'P1.COSTO_REPOSICION_2',
      costoPro1: 'P1.AVERAGE_COST',
      costoPro2: 'P1.AVERAGE_COST2'
    },
    filtro: {
      where: '',
      'order by': 'P1.PRODUCT_ID desc',
      limit: ' 10'
    }
  };

  listaProductos: any;
  error: any;

  listaCategoria : any;
  marcaSeleccionadaDato : any;

  idCategoriaSeleccionada : any;

  detOrden : any = [];

  cabOrden : any;

  constructor(private loadingService: LoadingService, private servicioProducto: ProductosService,
              private utilMensaje: UtilmensajeService, private router: Router,
              private controlParametros: ControlParametrosService, public viewCtrl: ModalController, private taskService : TasksServiceProvider,
               private controlAcceso: ControlaccesologinService) { 

               this.listaCategoria =   this.controlParametros.getParametro('lista_categoria');
               this.idCategoriaSeleccionada = '-1';

                this.detOrden =  <any []>this.controlParametros.getParametro('det_despacho');

                this.cabOrden = this.controlParametros.getParametro('cab_despacho');

                if (!this.detOrden){
                  this.detOrden = [];
                }

              }

  ngOnInit() {
    this.listaProductos = null;
    this.error = null;
  }

  marcaSeleccionada($event) {
    
    this.marcaSeleccionadaDato = $event;
    this.idCategoriaSeleccionada = this.marcaSeleccionadaDato.detail.value;
    console.log ("valor");
    console.log (this.marcaSeleccionadaDato.detail.value);

    this.loadingService.loadingPresent('Por favor espere');
    this.taskService.listaProductoCategoriaStock(this.marcaSeleccionadaDato.detail.value).then(res => {
      console.log(res );
      this.listaProductos = res;

      if (this.listaProductos.length < 1){
        this.utilMensaje.presentarMensaje('No se encontraron productos registrados');
      }

      this.loadingService.loadingDismiss();
    }, error => {
      this.listaProductos = null;
      console.log('Error al realizar la consulta ');
      this.loadingService.loadingDismiss();
      this.utilMensaje.presentarMensaje('Error al realizar la consulta de productos');
    });



  
  }

  
  buscarCodigo(valor) {
    console.log('Ejecutar Evento ' + valor);
    this.loadingService.loadingPresent('Por favor espere');

        if (this.idCategoriaSeleccionada === '-1'){

          this.taskService.listaProductoLikeCodigoStock(valor).then(data => {
          console.log(data);
          this.listaProductos = data;

          if (this.listaProductos.length < 1) {
            this.utilMensaje.presentarMensaje('No se encontraron productos registrados');
          }

          this.loadingService.loadingDismiss();
        }, error => {
          this.error = JSON.stringify(error);
          this.listaProductos = null;
          console.log('Error al realizar la consulta ');
          this.loadingService.loadingDismiss();
          this.utilMensaje.presentarMensaje('Error al realizar la consulta de productos');
        });
    }else {

        this.taskService.listaProductoLikeCodigoCatStock(valor, this.idCategoriaSeleccionada).then(data => {
          console.log(data);
          this.listaProductos = data;

          if (this.listaProductos.length < 1) {
            this.utilMensaje.presentarMensaje('No se encontraron productos registrados');
          }

          this.loadingService.loadingDismiss();
        }, error => {
          this.error = JSON.stringify(error);
          this.listaProductos = null;
          console.log('Error al realizar la consulta ');
          this.loadingService.loadingDismiss();
          this.utilMensaje.presentarMensaje('Error al realizar la consulta de productos');
        });

    }

    

  }

  buscarSearch(valor) {
    console.log('Ejecutar Evento ' + valor);
    this.loadingService.loadingPresent('Por favor espere');

     if (this.idCategoriaSeleccionada === '-1'){

          this.taskService.listaProductoLikeNombreStock(valor).then(data => {
            console.log(data);
            this.listaProductos = data;

            if (this.listaProductos.length < 1) {
              this.utilMensaje.presentarMensaje('No se encontraron productos registrados');
            }

            this.loadingService.loadingDismiss();
          }, error => {
            this.error = JSON.stringify(error);
            this.listaProductos = null;
            console.log('Error al realizar la consulta ');
            this.loadingService.loadingDismiss();
            this.utilMensaje.presentarMensaje('Error al realizar la consulta de productos');
          });
     }else {

         this.taskService.listaProductoLikeNombreCatStock(valor, this.idCategoriaSeleccionada).then(data => {
            console.log(data);
            this.listaProductos = data;

            if (this.listaProductos.length < 1) {
              this.utilMensaje.presentarMensaje('No se encontraron productos registrados');
            }

            this.loadingService.loadingDismiss();
          }, error => {
            this.error = JSON.stringify(error);
            this.listaProductos = null;
            console.log('Error al realizar la consulta ');
            this.loadingService.loadingDismiss();
            this.utilMensaje.presentarMensaje('Error al realizar la consulta de productos');
          });

     }
  }


  checkBlur(evt) {
    console.log('Ejecutar Evento ' + evt.srcElement.value );
    this.loadingService.loadingPresent('Por favor espere');
    this.postData.filtro.where = 'P1.PRODUCT_NAME like (\'%' + evt.srcElement.value + '%\')';
    console.log(this.postData);
    this.servicioProducto.getProductosCriterio(this.postData).subscribe(res => {
      console.log(res );
      this.listaProductos = res;
      this.loadingService.loadingDismiss();
    }, error => {
      this.error = JSON.stringify(error) ;
      this.listaProductos = null;
      console.log('Error al realizar la consulta ');
      this.loadingService.loadingDismiss();
      this.utilMensaje.presentarMensaje('Error al realizar la consulta de productos');
    });
  }

  getFechaActual()
  {
    var today = new Date();
                  var dd = String(today.getDate()).padStart(2, '0');
                  var mm = String(today.getMonth() + 1).padStart(2, '0'); 
                  var yyyy = today.getFullYear();
                  return mm + '/' + dd + '/' + yyyy;
  }

  procesarProducto(item) {

    let DetalleOrden = {
                        id: null,
                        estado: 'A',
                        idreferencia: null,
                        campo_auditoria : this.controlAcceso.getDataLogin().username,
                        fecha_actualizacion: this.getFechaActual(),
                        fecha_registro: this.getFechaActual(),
                        observacion: 'REGISTRO MOBILE',
                        cantidad: 1,
                        codigoauxiliar: item.CODIGOPRODUCTO,
                        codigoprincipal: item.CODIGOPRODUCTO,
                        descripcion: item.DESCRIPCION,
                        descuento: 0,
                        detallesadicionales: "",
                        preciototalconimpuesto: 0,
                        preciototalsinimpuesto: 0,
                        preciounitario: item.preciounitario,
                        id_cab_despacho: this.cabOrden.id,
                        id_producto: item.ID,
                        codigoproducto: item.CODIGOPRODUCTO,
                        numerofactura: this.cabOrden.numerofactura,
                        numerooden:    this.cabOrden.numerooden,
                        preciopedido: item.preciounitario, 
                        saldostock: item.saldo,                        
              };   

    const objEncontrado = this.detOrden.find(element => element.codigoprincipal === item.CODIGOPRODUCTO);

    if (!objEncontrado){

      this.detOrden.push(DetalleOrden);
      this.cabOrden.totalsinimpuestos =  this.cabOrden.totalsinimpuestos + item.preciounitario;
      this.cabOrden.importetotal =  this.cabOrden.importetotal + item.preciounitario;

      let totalSinImpuesto = 0;
      let totalImporte = 0;

      this.detOrden.forEach(element => {
            if (element.estado === 'A') {
               if (!element.cantidad)
                  element.cantidad = 1;

              if (element.cantidad <= 0 )
                element.cantidad = 1;  

              element.preciototalconimpuesto = element.preciounitario * element.cantidad;
              element.preciototalsinimpuesto = element.preciounitario * element.cantidad;
              totalSinImpuesto = totalSinImpuesto + element.preciototalsinimpuesto;
              totalImporte = totalImporte + element.preciototalconimpuesto; 
            }            
      });
      this.cabOrden.totalsinimpuestos=totalSinImpuesto;
      this.cabOrden.importetotal=totalImporte;

      this.controlParametros.setParametro('cab_despacho', this.cabOrden);
      this.controlParametros.setParametro("det_despacho", this.detOrden);
      this.viewCtrl.dismiss();
    }else{
      this.utilMensaje.presentarMensaje('El producto ' + item.CODIGOPRODUCTO + ', ya se encuentra en el despacho');
    }    
  }


  regresar() {
    this.viewCtrl.dismiss();
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
