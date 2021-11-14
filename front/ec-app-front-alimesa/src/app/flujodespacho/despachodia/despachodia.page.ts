import { Component, OnInit } from '@angular/core';
import {LoadingService} from '../../servicios/transversales/loading.service';
import {UtilmensajeService} from '../../servicios/transversales/utilmensaje.service';
import {Router} from '@angular/router';
import {ControlParametrosService} from '../../servicios/transversales/control-parametros.service';
import {ClientesService} from '../../servicios/ventas/clientes/clientes.service';
import { TasksServiceProvider } from '../../providers/tasks-service';
import { ImprimirPage } from '../../imprimir/imprimir.page';
import { ModalController } from '@ionic/angular';
import { DatePipe } from '@angular/common';
import { DecimalPipe } from '@angular/common';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-despachodia',
  templateUrl: './despachodia.page.html',
  styleUrls: ['./despachodia.page.scss'],
})
export class DespachodiaPage implements OnInit {

  listaDespachos : any;
  fechaHoy : any;
  tamanioLinea : number = 32;

  constructor(private loadingService: LoadingService, private servicioCliente: ClientesService,
              private utilMensaje: UtilmensajeService, private router: Router,
              private controlParametros: ControlParametrosService,
               public viewCtrl: ModalController,
               private datePipe: DatePipe, 
               private decimalPipe: DecimalPipe,
               private currencyPipe:CurrencyPipe,
              private tasksService: TasksServiceProvider) { }

  
  ngOnInit() {
  
  }


ionViewDidEnter() {

   this.loadingService.loadingPresent('Cargando Órdenes...');
       var today = new Date();
       var dd = String(today.getDate()).padStart(2, '0');
       var mm = String(today.getMonth() + 1).padStart(2, '0'); 
       var yyyy = today.getFullYear();
       this.fechaHoy = dd+"/"+mm+"/"+yyyy;
       this.tasksService.obtenerListaDespachosRealizados().then(res => {
          this.listaDespachos = res;
          this.loadingService.loadingDismiss();
      });         
  }
  

  crearCliente() {
    this.router.navigate(['/crearcliente'] );
  }

  
  procesarCliente(item) {
  
  }

   buscarCliente( patronNombre ){
   this.loadingService.loadingPresent('Cargando Despachos...');

   this.tasksService.listaDespachoNombre(patronNombre).then(res => {
                   this.listaDespachos = res;
                   this.loadingService.loadingDismiss();
    });

 }

 procesarDespacho(item) {   
   
   this.tasksService.listaDetalleDespacho(item).then( resultadoDetalle => { 
            if (resultadoDetalle.length > 0) { 
                  this.controlParametros.setParametro('cab_despacho', item);
                  this.controlParametros.setParametro("det_despacho", resultadoDetalle);
                  this.router.navigate(['/detalledespacho'] );                  
              }else {
                  this.utilMensaje.presentarMensaje('No existen detalles para el despacho : ' + item.numerooden );  
              }
        });
    }


    imprimir(item) {

    
    console.log("imprimirDespacho...."+JSON.stringify(item));    
    var textoImprimir = "";   

    //Cabecera Impresion
    textoImprimir = textoImprimir + this.centarTexto("EXPOTUNA")+"\n\n\r";
    textoImprimir = textoImprimir + this.centarTexto("Nota de Entrega No. " + item.numerooden)+"\n\n\r"; 
    textoImprimir = textoImprimir + this.centarTexto(item.razonsocialcomprador)+"\n\r"; 
    textoImprimir = textoImprimir + this.centarTexto(item.direccioncomprador)+"\n\r"; 
    textoImprimir = textoImprimir + this.centarTexto(item.identificacioncomprador)+"\n\n\r"; 
    textoImprimir = textoImprimir + "FECHA:        " + this.datePipe.transform(item.fechaemision,"dd-MM-yyyy")+"\n\r"; 
    textoImprimir = textoImprimir + "CODIGO:       " + item.codigoticket + "\n\r";         
    textoImprimir = textoImprimir + "CI/RUC CHOFER:" + item.cichofer + "\n\r"; 
    textoImprimir = textoImprimir + "CHOFER:       " + item.nombrechofer + "\n\r"; 
    textoImprimir = textoImprimir + "CAMION:       " + item.camion + "\n\r"; 
    textoImprimir = textoImprimir + "PLACA:        " + item.placavehiculo + "\n\r"; 
    textoImprimir = textoImprimir + "Fecha Aut:    " + item.fechaaut + "\n\r"; 
    textoImprimir = textoImprimir + "Autoriza:     " + item.autorizacion + "\n\r"; 
    textoImprimir = textoImprimir + "Clave Acc:    " + item.claveacceso + "\n\r"; 
    textoImprimir = textoImprimir + "VENDEDOR:     " + item.nombrevendedor+"\n\r"; 
    textoImprimir = textoImprimir + "TELEF:        " + item.telefono + "\n\r"; 
    //Documentos Aplicados
    textoImprimir = textoImprimir + "================================\n\r";
    textoImprimir = textoImprimir + this.centarTexto("Descripción")+"\n\r"; 
    textoImprimir = textoImprimir + this.completarIzquierdaDerecha("Cant.",2, 2) + this.completarIzquierdaDerecha("Precio U.", 2, 1) + this.completarIzquierdaDerecha("Total", 2, 2)+"\n\r"; 
    textoImprimir = textoImprimir + "================================\n\r";

    this.tasksService.listaDetalleDespacho(item).then( resultadoDetalle => { 
            if (resultadoDetalle.length > 0) { 
                 resultadoDetalle.forEach(element => {
                  textoImprimir = textoImprimir + this.maximoEspacio(element.descripcion) + "\n\r";

                  textoImprimir = textoImprimir + this.completarIzquierdaDerecha_(this.decimalPipe.transform(element.cantidad, '1.2-2'), 10, 0) +
                                                  this.completarIzquierdaDerecha_(this.currencyPipe.transform(element.preciounitario), 10, 0) +     
                                                  this.completarIzquierdaDerecha_(this.currencyPipe.transform(element.preciototalsinimpuesto), 12, 0) +  "\n\r";
                                                    
                });

                textoImprimir = textoImprimir + "\n\n\n\r";

                textoImprimir = textoImprimir + this.completarIzquierdaDerecha_( 
                                                    "Subtotal" + this.completarIzquierdaDerecha_(this.currencyPipe.transform(item.totalsinimpuestos), 12, 0) 
                                                    , 32 , 0)+ "\n\r";
                textoImprimir = textoImprimir + this.completarIzquierdaDerecha_( 
                                                  "Tarifa 0%"+ this.completarIzquierdaDerecha_(this.currencyPipe.transform(item.totalsinimpuestos), 12, 0)
                                                  , 32 , 0)+ "\n\r";
                textoImprimir = textoImprimir + this.completarIzquierdaDerecha_( 
                                                  "Tarifa 12%" + this.completarIzquierdaDerecha_(this.currencyPipe.transform(item.subtotal12), 12, 0)
                                                  , 32 , 0)+ "\n\r";
                textoImprimir = textoImprimir + this.completarIzquierdaDerecha_( 
                                                  "Impuesto"+ this.completarIzquierdaDerecha_(this.currencyPipe.transform(item.totalimpuesto), 12, 0)
                                                  , 32 , 0)+ "\n\r";
                textoImprimir = textoImprimir + this.completarIzquierdaDerecha_( 
                                                "Total"+ this.completarIzquierdaDerecha_(this.currencyPipe.transform(item.importetotal), 12, 0)
                                                , 32 , 0);
                textoImprimir = textoImprimir + "\n\n\n\r";

                textoImprimir = textoImprimir + this.centarTexto("Su documento electrónico será" )+ "\n\r";
                textoImprimir = textoImprimir + this.centarTexto("enviado a su correo electrónico")+ "\n\r";

                textoImprimir = textoImprimir + "\n\r";
                
                textoImprimir = textoImprimir + "\n\n\n\n\n________________________________\n\r";
                textoImprimir = textoImprimir + this.centarTexto("Cliente")+"\n\r"; 
                this.controlParametros.setParametro('despacho_imp', textoImprimir);              
                                       
                this.openModal();
              }else {
                  this.utilMensaje.presentarMensaje('No existen detalles para el despacho : ' + item.numerooden );  
              }
        });
    }

    async openModal() {
    const modal = await this.viewCtrl.create({
      component: ImprimirPage,
      cssClass: 'my-custom-modal-css'

    });
     modal.onDidDismiss().then((dataReturned) => {
    });
    return await modal.present();
  }

  centarTexto (frase: String) {
    if (!frase)
      frase = "";
    let tamanioCadena = frase.length;
    let caracteresSobrantes = this.tamanioLinea - tamanioCadena;
    let caracteresIzquierda = caracteresSobrantes / 2;
    
    console.log ("Caracteres a la izquiierda " + caracteresIzquierda ); 

    return frase.padStart(caracteresIzquierda + tamanioCadena);
    /*
    str.padStart(targetLength [, padString])
    str.padEnd(targetLength [, padString])
    */
  }

  completarIzquierdaDerecha (frase: String, izquierda : number, derecha: number ) {
    let fraseIzuierda = frase.padStart(frase.length + izquierda);
    return fraseIzuierda.padEnd(frase.length + izquierda + derecha)
  }

  textoMitad(frase: String, posicion: String){
    if (posicion === 'D')
      return frase.padStart( this.tamanioLinea/2);

    if (posicion === 'I')  
      return frase.padEnd(this.tamanioLinea/2)
  }

  maximoEspacio(frase: String) {

    if (frase.length > 32) {
      return frase.substring(0, 32);
    }

    return frase;

  }

  completarIzquierdaDerecha_ (frase: String, izquierda : number, derecha: number ) {
    let fraseIzuierda = frase.padStart(izquierda);
    return fraseIzuierda.padEnd(derecha)
  }

}
