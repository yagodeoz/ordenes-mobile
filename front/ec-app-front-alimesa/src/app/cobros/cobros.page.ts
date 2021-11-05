import { Component, OnInit } from '@angular/core';
import {LoadingService} from '../servicios/transversales/loading.service';
import {UtilmensajeService} from '../servicios/transversales/utilmensaje.service';
import {Router} from '@angular/router';
import {ControlParametrosService} from '../servicios/transversales/control-parametros.service';
import {ClientesService} from '../servicios/ventas/clientes/clientes.service';
import { TasksServiceProvider } from '../providers/tasks-service';

@Component({
  selector: 'app-cobros',
  templateUrl: './cobros.page.html',
  styleUrls: ['./cobros.page.scss'],
})
export class CobrosPage implements OnInit {

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
  listaCobros: any;

  constructor(private loadingService: LoadingService, private servicioCliente: ClientesService,
              private utilMensaje: UtilmensajeService, private router: Router,
              private controlParametros: ControlParametrosService, private taskService : TasksServiceProvider) { }

  ngOnInit() {
    this.listaClientes = null;
  }

  crearCliente() {
    this.router.navigate(['/crearcliente'] );
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


  procesarClienteEfectivo(item) {
    this.loadingService.loadingPresent('Consultando cartera, por favor espere....');
    
    
    this.taskService.listaCobrosCliente(item).then(res => {
      console.log(res );
      this.listaCobros = res;

      if (this.listaCobros.length < 1){
        this.listaCobros = null;
        this.utilMensaje.presentarMensaje('No se encontraron documentos pendientes de pago, para el cliente: ' + item.NOMBRECOMPLETO);
        this.loadingService.loadingDismiss();
      }else {
          console.log(item);
          this.controlParametros.setParametro('lista_cobros', this.listaCobros);
          this.controlParametros.setParametro('cliente_item', item);
          this.router.navigate(['/cabeceracobro'] );
      }      
    }, error => {
      this.listaCobros = null;
      console.log('Error al realizar la consulta ');
      this.loadingService.loadingDismiss();      
      this.utilMensaje.presentarMensaje('Error al realizar la consulta de cartera');
    });

   
  
  }

  procesarClienteBanco(item) {
    
    this.loadingService.loadingPresent('Consultando cartera, por favor espere....');    
    
    this.taskService.listaCobrosCliente(item).then(res => {
      console.log(res );
      this.listaCobros = res;

      if (this.listaCobros.length < 1){
        this.listaCobros = null;
        this.utilMensaje.presentarMensaje('No se encontraron documentos pendientes de pago, para el cliente: ' + item.NOMBRECOMPLETO);
        this.loadingService.loadingDismiss();
      }else {

          this.taskService.listaBancos().then(res => {
          console.log(item);
          this.controlParametros.setParametro('lista_bancos', res);
          this.controlParametros.setParametro('lista_cobros', this.listaCobros);
          this.controlParametros.setParametro('cliente_item', item);
          this.router.navigate(['/cabeceracobrobanco'] );
          
          });
      
      
      }      
    }, error => {
      this.listaCobros = null;
      console.log('Error al realizar la consulta ');
      this.loadingService.loadingDismiss();      
      this.utilMensaje.presentarMensaje('Error al realizar la consulta de cartera');
    });    
  
  }

}
