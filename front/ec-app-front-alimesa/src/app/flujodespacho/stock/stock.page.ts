import { Component, OnInit } from '@angular/core';
import {LoadingService} from '../../servicios/transversales/loading.service';
import {UtilmensajeService} from '../../servicios/transversales/utilmensaje.service';
import {Router} from '@angular/router';
import {ControlParametrosService} from '../../servicios/transversales/control-parametros.service';
import {ClientesService} from '../../servicios/ventas/clientes/clientes.service';
import { TasksServiceProvider } from '../../providers/tasks-service';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.page.html',
  styleUrls: ['./stock.page.scss'],
})
export class StockPage implements OnInit {

  listaClientes: any;
  listaCategoria : any;
  listaProductos : any;
  error : any;
  idCategoriaSeleccionada : any;
  marcaSeleccionadaDato : any;
  fechaHoy : any;

  constructor(private loadingService: LoadingService, private servicioCliente: ClientesService,
              private utilMensaje: UtilmensajeService, private router: Router,
              private controlParametros: ControlParametrosService, private taskService : TasksServiceProvider) { }

  ngOnInit() {
    this.listaClientes = null;
  }

  crearCliente() {
    this.router.navigate(['/crearcliente'] );
  }

  buscarCliente(valor) {
   
  }


  procesarCliente(item) {
  
  }

  ionViewDidEnter() {
   this.loadingService.loadingPresent('Cargando Stock...');
       var today = new Date();
       var dd = String(today.getDate()).padStart(2, '0');
       var mm = String(today.getMonth() + 1).padStart(2, '0'); 
       var yyyy = today.getFullYear();
       this.fechaHoy = dd+"/"+mm+"/"+yyyy;
       this.taskService.listaCategoriaProducto().then(res => {
            this.listaCategoria = res;  

            this.taskService.listaProductoLikeCodigoStock_('').then(data => {
                console.log(data);
                this.listaProductos = data;

                if (this.listaProductos.length < 1) {
                    this.utilMensaje.presentarMensaje('No se encontraron productos registrados');
                }
                this.loadingService.loadingDismiss();
            });
       });
  }

    marcaSeleccionada($event) {
    
    this.marcaSeleccionadaDato = $event;
    this.idCategoriaSeleccionada = this.marcaSeleccionadaDato.detail.value;
    console.log ("valor");
    console.log (this.marcaSeleccionadaDato.detail.value);

    this.loadingService.loadingPresent('Por favor espere');
    this.taskService.listaProductoCategoriaStock_(this.marcaSeleccionadaDato.detail.value).then(res => {
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

          this.taskService.listaProductoLikeCodigoStock_(valor).then(data => {
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

        this.taskService.listaProductoLikeCodigoCatStock_(valor, this.idCategoriaSeleccionada).then(data => {
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

          this.taskService.listaProductoLikeNombreStock_(valor).then(data => {
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

         this.taskService.listaProductoLikeNombreCatStock_(valor, this.idCategoriaSeleccionada).then(data => {
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

}
