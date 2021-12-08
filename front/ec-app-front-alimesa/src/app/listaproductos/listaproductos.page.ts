import { Component, OnInit } from '@angular/core';
import { LoadingService } from '../servicios/transversales/loading.service';
import { ProductosService } from "../servicios/ventas/productos/productos.service";
import { UtilmensajeService } from "../servicios/transversales/utilmensaje.service";
import { Router } from "@angular/router";
import { ControlParametrosService } from "../servicios/transversales/control-parametros.service";
import { TasksServiceProvider } from '../providers/tasks-service';

@Component({
  selector: 'app-listaproductos',
  templateUrl: './listaproductos.page.html',
  styleUrls: ['./listaproductos.page.scss'],
})
export class ListaproductosPage implements OnInit {

  listaProductos: any;
  error: any;

  constructor(private loadingService: LoadingService, private servicioProducto: ProductosService,
    private utilMensaje: UtilmensajeService, private router: Router,
    private controlParametros: ControlParametrosService, private taskService: TasksServiceProvider) { }

  ngOnInit() {
    this.listaProductos = null;
    this.error = null;
  }


  buscarCodigo(valor) {
    console.log('Ejecutar Evento ' + valor);
    this.loadingService.loadingPresent('Por favor espere');

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
  }

  buscarSearch(valor) {
    console.log('Ejecutar Evento ' + valor);
    this.loadingService.loadingPresent('Por favor espere');

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
  }

  checkBlur(evt) {
    console.log('Ejecutar Evento ' + evt.srcElement.value);
    this.loadingService.loadingPresent('Por favor espere');


    this.taskService.listaProductoLikeNombre(evt.srcElement.value).then(data => {
      console.log(data);
      this.listaProductos = data;
      this.loadingService.loadingDismiss();


    }, error => {
      this.error = JSON.stringify(error);
      this.listaProductos = null;
      console.log('Error al realizar la consulta ');
      this.loadingService.loadingDismiss();
      this.utilMensaje.presentarMensaje('Error al realizar la consulta de productos');
    });
  }


  procesarProducto(item) {
    this.loadingService.loadingPresent('Por favor espere');
    this.taskService.productoLikeNombre(item.NOMBRE).then(data => {

        this.loadingService.loadingDismiss();
        console.log(item);
        let productos = data;

        if (productos.length < 1) {
            this.utilMensaje.presentarMensaje('No se encontraron productos registrados');          
        }else {
           this.controlParametros.setParametro('prod_item', productos[0]);
          this.router.navigate(['/producto']); 
        }

      }, error => {
      this.error = JSON.stringify(error);
      this.listaProductos = null;
      console.log('Error al realizar la consulta ');
      this.loadingService.loadingDismiss();
      this.utilMensaje.presentarMensaje('Error al realizar la consulta de productos');
    });    
  }

}
