import { Component, OnInit } from '@angular/core';
import {LoadingService} from '../../servicios/transversales/loading.service';
import {UtilmensajeService} from '../../servicios/transversales/utilmensaje.service';
import {Router} from '@angular/router';
import {ControlParametrosService} from '../../servicios/transversales/control-parametros.service';
import {ClientesService} from '../../servicios/ventas/clientes/clientes.service';
import { TasksServiceProvider } from '../../providers/tasks-service';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-cobrosdia',
  templateUrl: './cobrosdia.page.html',
  styleUrls: ['./cobrosdia.page.scss'],
})
export class CobrosdiaPage implements OnInit {

  listaCobrosDia : any;
  fechaHoy : any;
  detallesPago : any;

  constructor(private loadingService: LoadingService, private servicioCliente: ClientesService,
              private utilMensaje: UtilmensajeService, private router: Router,
              private controlParametros: ControlParametrosService, private tasksService: TasksServiceProvider, public alertController: AlertController) {

               }

  ngOnInit() {
    
  }

  ionViewDidEnter() {

   this.loadingService.loadingPresent('Cargando pagos realizados...');
       var today = new Date();
       var dd = String(today.getDate()).padStart(2, '0');
       var mm = String(today.getMonth() + 1).padStart(2, '0'); 
       var yyyy = today.getFullYear();
       this.fechaHoy = dd+"/"+mm+"/"+yyyy;

       this.tasksService.listaCobrosDia().then(res => {
          this.listaCobrosDia = res;
          this.loadingService.loadingDismiss();
      });         
  }

 
 buscarCliente( patronNombre ){
  this.loadingService.loadingPresent('Cargando pagos realizados...');

   this.tasksService.listaCobrosDiaNombre(patronNombre).then(res => {
                   this.listaCobrosDia = res;
                   this.loadingService.loadingDismiss();
    });

 }



 procesarCobro(item) {
    
    this.loadingService.loadingPresent('Consultando detalles...');
    this.tasksService.pagosRealizadosNumeroPago(item.numeropago).then( resultado => {
        
      console.log( resultado );
      this.detallesPago = resultado;

      if (this.detallesPago.length < 1){
        this.detallesPago = null;
        this.utilMensaje.presentarMensaje('No se encontraron detalles de pago, para el cliente: ' + item.nombrecliente);
        this.loadingService.loadingDismiss();
      }else {
        this.controlParametros.setParametro('detalles', this.detallesPago);
        this.controlParametros.setParametro('comprobante', item);
        this.router.navigate(['/detallescobro'] );               
      }      
    }, error => {
      this.detallesPago = null;
      console.log('Error al realizar la consulta ');
      this.loadingService.loadingDismiss();      
      this.utilMensaje.presentarMensaje('Error al realizar la consulta de detalles pagos');
    });    
    }



    getDescripcionEstado(item){

      if (item.estado === 'C')
        return "Pago Creado";
      else
        return "Pago Replicado";  

    }
}
