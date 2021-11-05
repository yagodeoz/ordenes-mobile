import { Component, OnInit } from '@angular/core';
import {ControlParametrosService} from '../../servicios/transversales/control-parametros.service';
import {Router} from '@angular/router';
import { ModalController } from '@ionic/angular';
import { ControlaccesologinService } from '../../servicios/login/controlaccesologin.service';
import {LoadingService} from '../../servicios/transversales/loading.service';
import { UtilmensajeService } from "../../servicios/transversales/utilmensaje.service";
import { AlertController } from '@ionic/angular';
import { TasksServiceProvider } from '../../providers/tasks-service';

@Component({
  selector: 'app-cabeceracobros',
  templateUrl: './cabeceracobros.page.html',
  styleUrls: ['./cabeceracobros.page.scss'],
})
export class CabeceracobrosPage implements OnInit {

 item: any;
 compPago : any;
 listaCobros : any;
 registroPago : {
   cabCobro : any;
   valor  : number;
 }

 listaRegistroPago : any = [];

  constructor(private utilMensaje: UtilmensajeService, private controlParametros: ControlParametrosService, private router: Router, public viewCtrl: ModalController,
              private controlAcceso: ControlaccesologinService, private loadingService: LoadingService, public alertController: AlertController,
              private tasksService: TasksServiceProvider
              ) { 

                  this.item = this.controlParametros.getParametro('cliente_item');
                  console.log (this.item); 

                  

                  this.compPago = {
                     id: null,
                     estado: 'C',
                    idreferencia: null, 
                    campo_auditoria: this.controlAcceso.getDataLogin().username,
                    fecha_actualizacion: null,
                    fecha_registro:  this.getFechaActual(),
                    observacion: "REG-APP-MOBILE",   
                    fechaemision: this.getFechaActual(),
                    fechavencimiento: null,
                    idtipoformapago: "EFECTIVO",
                    monto: 0,
                    numeropago: new Date().getTime(),
                    comprobante: '',
                    banco: null,
                    codigobanco: null,
                    cuenta: null,
                    cheque: null,
                    valorcheque: null,
                    usuarioasignado: this.controlAcceso.getDataLogin().username,
                    usuarioasignante: this.controlAcceso.getDataLogin().username,
                    codigocliente: this.item.CODCLIENTE,
                    nombrecliente: this.item.NOMBRECOMPLETO,
                    identificacion: this.item.IDENTIFICACION                
                  };   

                  this.listaCobros = this.controlParametros.getParametro('lista_cobros');

                  this.listaCobros.forEach ( cobro => {
                        this.listaRegistroPago.push ( { "cobro": cobro, 
                                                        "valor": 0.0, 
                                                        "pagosrealizados" : cobro.pagosrealizados,
                                                        "cobrar" :  cobro.pagosrealizados < cobro.saldo,
                                                        "saldovalidador" : cobro.saldo - cobro.pagosrealizados
                                                          } );
                  });

                   this.loadingService.loadingDismiss();   
 
              }

  getFechaActual()
  {
    var today = new Date();
                  var dd = String(today.getDate()).padStart(2, '0');
                  var mm = String(today.getMonth() + 1).padStart(2, '0'); 
                  var yyyy = today.getFullYear();
                  return mm + '/' + dd + '/' + yyyy;
  }

  ngOnInit() {
    
  }

  noImplementado() {
    alert('No implementado por falta de definción');
  }

  editarCliente() {

    this.router.navigate(['/editcliente'] );
  }

  levantarBusqueda() {
    //this.openModal();
  }
/*
  async openModal() {
    const modal = await this.viewCtrl.create({
      component: BuecarproductospedidoPage,
      cssClass: 'my-custom-modal-css'
    });
    return await modal.present();
  }
*/

  calcularDatos (evento: any) {


    //validar mvalor vs saldo
    //obtener el ID y buscar en la lista de cobros 
    //validar el saldo vs valor

    const regCobroValidar = this.listaRegistroPago.filter(elemento => { 
                              elemento.cobro.numerofactura ===  evento.id 
                            });

    if (regCobroValidar.saldovalidador < evento.value)                         
    {
        this.utilMensaje.presentarMensaje('El valor ingresado es mayor al saldo del documento : ' + regCobroValidar.cobro.numerofactura);               
    }

    let totalPagoEfectivo = 0;
    this.listaRegistroPago.forEach(element => {
       
       if ( element.cobro.numerofactura ===  evento.id ){
          if (element.saldovalidador < evento.value){
            element.valor = 0.0;
            evento.value = 0.0;
          }
       }
       totalPagoEfectivo = totalPagoEfectivo + element.valor;
    });

    this.compPago.monto  =  totalPagoEfectivo;
  } 




  registrarPago() {

    if (this.compPago.monto <= 0){
       this.utilMensaje.presentarMensaje('No ha registrado ningun pago');
      return;
    }

   this.alertController.create({
      header: 'Pago de documentos',
      subHeader: 'Pago Total:  $ ' + this.compPago.monto,
      message: 'Está seguro de continuar?',
      buttons: [
        {
          text: 'Cancelar',
          handler: () => {
            console.log('pago cancelado');
          }
        },
        {
          text: 'Continuar',
          handler: () => {
              this.salvarPago().then(() => {
              this.router.navigateByUrl('/folder/PrincipalLogin');
           });
          }
        }
        
      ]
    }).then(res => {
      res.present();
    });

  }

   salvarPago(){

      this.loadingService.loadingPresent('Procesando, por favor espere...');
      return new Promise((resolve, reject) => {      
            let sqlGeneral = 'INSERT OR REPLACE INTO [TABLA]([CAMPOS]) VALUES([VALORES]);';
            let sqlInsert: Array<string> = [];

            let sqlCabecera = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_COMPROBANTECOBRO);
                sqlCabecera = sqlCabecera.replace('[CAMPOS]', "estado, idreferencia, campo_auditoria, fecha_actualizacion, fecha_registro, observacion, " +
                                  "fechaemision, fechavencimiento, idtipoformapago, monto, numeropago, comprobante, banco, " +
                                  "codigobanco, cuenta, cheque, valorcheque, usuarioasignado, usuarioasignante, codigocliente, nombrecliente, " +
                                  "identificacion");
              
          sqlInsert.push(sqlCabecera.replace('[VALORES]', 
            "\"" + this.compPago.estado + "\"," +
            "\"" + this.compPago.idreferencia + "\"," +
            "\"" + this.compPago.campo_auditoria + "\"," +
            "\"" + this.compPago.fecha_actualizacion + "\"," + 
            "\"" + this.compPago.fecha_registro + "\"," +
            "\"" + this.compPago.observacion + "\"," +
            "\"" + this.compPago.fechaemision + "\"," +
            "\"" + this.compPago.fechavencimiento + "\"," +
            "\"" + this.compPago.idtipoformapago + "\"," +
            "\"" + this.compPago.monto + "\"," +
            "\"" + this.compPago.numeropago + "\"," +
            "\"" + this.compPago.comprobante + "\"," +
            "\"" + this.compPago.banco + "\"," +
            "\"" + this.compPago.codigobanco + "\"," +
            "\"" + this.compPago.cuenta + "\"," +
            "\"" + this.compPago.cheque + "\"," +
            "\"" + this.compPago.valorcheque + "\"," +
            "\"" + this.compPago.usuarioasignado + "\"," +
            "\"" + this.compPago.usuarioasignante + "\"," +
            "\"" + this.compPago.codigocliente + "\"," +
            "\"" + this.compPago.nombrecliente + "\"," +
            "\"" + this.compPago.identificacion+ "\"")); 

          console.log(sqlInsert);
        this.tasksService.db.sqlBatch(sqlInsert)
          .then(() => {
   
             let sqlInsert: Array<string> = [];
             let sqlDetalle = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_DETALLECOBRO);
             sqlDetalle = sqlDetalle.replace('[CAMPOS]',  " estado, idreferencia, campo_auditoria, fecha_actualizacion, fecha_registro, observacion, " +
                                  "codigobanco, cuenta, fechavencimiento, idbanco, nombrebanco, numerocheque, numerocomprobante, " +
                                  "numeropago, tipopago, valorcheque, valorpago, id_cab_cobro, numerocomprobanteerp, numerodocumento, numerofactura, " +
                                  "numeropagoerp");
             
             
             
              this.listaRegistroPago.forEach(element => {

              if (element.valor > 0 ) {
                  sqlInsert.push(sqlDetalle.replace('[VALORES]', 
                "\"A\"," +
                "\"" + element.cobro.id + "\"," +
                "\"" + this.controlAcceso.getDataLogin().username + "\"," +
                "\"" + this.getFechaActual() + "\"," + 
                "\"" + this.getFechaActual() + "\"," +
                "\"  REGISTRO DE PAGO MOBILE \"," +
                "\" \"," +
                "\" \"," +
                 "\"" + element.cobro.fechavencimiento + "\"," +
                "\" \"," +
                "\" \"," +
                "\" \"," +
                "\"" + this.compPago.comprobante + "\"," +
                "\"" + this.compPago.numeropago + "\"," +
                "\"EFECTIVO\"," +
                "\" \"," +
                "\"" + element.valor + "\"," +
               "\"" + element.cobro.id + "\"," +
                "\" \"," +
                "\"" + element.cobro.numerodocumento + "\"," +
                "\"" + element.cobro.numerofactura + "\"," +               
                "\" \""));

              } 

            });

              console.log(sqlInsert);
             this.tasksService.db.sqlBatch(sqlInsert)
          .then(() => {                 
             console.log("Registrado correctamente");
             this.controlParametros.removerParametro('lista_cobros');            
             resolve(true);           

          });
             
          });
      });  

  }


}
