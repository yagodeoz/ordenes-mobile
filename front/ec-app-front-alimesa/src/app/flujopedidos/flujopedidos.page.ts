import { Component, OnInit } from '@angular/core';
import {LoadingService} from '../servicios/transversales/loading.service';
import {UtilmensajeService} from '../servicios/transversales/utilmensaje.service';
import {Router} from '@angular/router';
import {ControlParametrosService} from '../servicios/transversales/control-parametros.service';
import {ClientesService} from '../servicios/ventas/clientes/clientes.service';
import { TasksServiceProvider } from '../providers/tasks-service';

@Component({
  selector: 'app-flujopedidos',
  templateUrl: './flujopedidos.page.html',
  styleUrls: ['./flujopedidos.page.scss'],
})
export class FlujopedidosPage implements OnInit {

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

  constructor(private loadingService: LoadingService, private servicioCliente: ClientesService,
              private utilMensaje: UtilmensajeService, private router: Router,
              private controlParametros: ControlParametrosService, private taskService : TasksServiceProvider) { }

  ngOnInit() {
    this.listaClientes = null;
  }

  buscarClienteRF(valor) {
    this.loadingService.loadingPresent('Por favor espere');
    console.log('Ejecutar Evento ' + valor );
    this.taskService.listaClienteLikeRegistroFiscal(valor).then(res => {
      console.log(res );
      this.listaClientes = res;
      this.listaClientes = res;

      if (this.listaClientes.length < 1){
        this.utilMensaje.presentarMensaje('No se encontraron clientes registrados');
      }

      this.loadingService.loadingDismiss();
    }, error => {
      this.listaClientes = null;
      console.log('Error al realizar la consulta ');
      this.loadingService.loadingDismiss();
      this.utilMensaje.presentarMensaje('Error al realizar la consulta de clientes');
    });
  }

  buscarCliente(valor) {
    this.loadingService.loadingPresent('Por favor espere');
    console.log('Ejecutar Evento ' + valor );
    this.taskService.listaClienteLikeNombre(valor).then(res => {
      console.log(res );
      this.listaClientes = res;

      if (this.listaClientes.length < 1){
        this.utilMensaje.presentarMensaje('No se encontraron clientes registrados');
      }

      this.loadingService.loadingDismiss();
    }, error => {
      this.listaClientes = null;
      console.log('Error al realizar la consulta ');
      this.loadingService.loadingDismiss();
      this.utilMensaje.presentarMensaje('Error al realizar la consulta de clientes');
    });
  }



  procesarCliente(item) {
    
    this.taskService.listaCategoriaProducto ().then(res => {

    this.controlParametros.setParametro('cliente_item', item);
    this.controlParametros.setParametro('editar_orden', 'N');
    this.controlParametros.setParametro('lista_categoria', res);
    this.router.navigate(['/cabecerapedido'] );
  
  
  }, error => {
      this.listaClientes = null;
      console.log('Error al realizar la consulta ');
      this.loadingService.loadingDismiss();
      this.utilMensaje.presentarMensaje('Error al realizar la consulta de clientes');
    });

  }

}
