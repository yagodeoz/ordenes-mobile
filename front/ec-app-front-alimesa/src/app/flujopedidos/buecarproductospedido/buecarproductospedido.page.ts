import { Component, OnInit } from '@angular/core';
import {LoadingService} from '../../servicios/transversales/loading.service';
import {ProductosService} from '../../servicios/ventas/productos/productos.service';
import {UtilmensajeService} from '../../servicios/transversales/utilmensaje.service';
import {Router} from '@angular/router';
import {ControlParametrosService} from '../../servicios/transversales/control-parametros.service';
import {ModalController} from '@ionic/angular';
import { TasksServiceProvider } from '../../providers/tasks-service';

@Component({
  selector: 'app-buecarproductospedido',
  templateUrl: './buecarproductospedido.page.html',
  styleUrls: ['./buecarproductospedido.page.scss'],
})
export class BuecarproductospedidoPage implements OnInit {

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
              private controlParametros: ControlParametrosService, public viewCtrl: ModalController, private taskService : TasksServiceProvider) { 

               this.listaCategoria =   this.controlParametros.getParametro('lista_categoria');
               this.idCategoriaSeleccionada = '-1';

                this.detOrden =  <any []>this.controlParametros.getParametro('det_orden');

                this.cabOrden = this.controlParametros.getParametro('cab_orden');

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
    this.taskService.listaProductoCategoria(this.marcaSeleccionadaDato.detail.value).then(res => {
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

          this.taskService.listaProductoLikeCodigo(valor).then(data => {
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

        this.taskService.listaProductoLikeCodigoCat(valor, this.idCategoriaSeleccionada).then(data => {
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

          this.taskService.listaProductoLikeNombre(valor).then(data => {
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

         this.taskService.listaProductoLikeNombreCat(valor, this.idCategoriaSeleccionada).then(data => {
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


  procesarProducto(item) {

    let DetalleOrden = {
                        ID : null,
                        ESTADO : 'A',
                        IDREFERENCIA : this.cabOrden.IDREFERENCIA,
                        FECHAACTUALIZACION : '',
                        FECHAREGISTRO : '',
                        OBSERVACION : 'REGISTRO APP MOBILE',
                        CANTIDAD : 1,
                        CODIGOAUXILIAR : item.CODIGOAUXILIAR,
                        CODIGOPRINCIPAL : item.CODIGOPRODUCTO,
                        DESCRIPCION : item.DESCRIPCION,
                        DESCUENTO : 0,
                        DETALLESADICIONALES : '',
                        PRECIOTOTALCONIMPUESTO : item.PRECIOUNITARIO,
                        PRECIOTOTALSINIMPUESTO : item.PRECIOUNITARIO,
                        PRECIOUNITARIO : item.PRECIOUNITARIO,
                        ID_CABECERA : null,
                        ID_PRODUCTO : item.ID,
                        NUMEROORDEN : '',
              };   

    const objEncontrado = this.detOrden.find(element => element.CODIGOPRINCIPAL === item.CODIGOPRODUCTO);

    if (!objEncontrado){

      this.detOrden.push(DetalleOrden);
      this.cabOrden.TOTALSINIMPUESTOS =  this.cabOrden.TOTALSINIMPUESTOS + item.PRECIOUNITARIO;
      this.cabOrden.IMPORTETOTAL =  this.cabOrden.IMPORTETOTAL + item.PRECIOUNITARIO;

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

      this.controlParametros.setParametro("det_orden", this.detOrden);
      this.controlParametros.setParametro('cab_orden', this.cabOrden);
      this.viewCtrl.dismiss();
    }else{
      this.utilMensaje.presentarMensaje('El producto ' + item.CODIGOPRODUCTO + ', ya se encuentra en la órden');
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
