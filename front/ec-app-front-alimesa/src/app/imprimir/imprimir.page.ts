import { Component } from '@angular/core';
import { AlertController }  from '@ionic/angular';
import {PrintProvider} from '../providers/print-service';
import {ControlParametrosService} from '../servicios/transversales/control-parametros.service';
import {ModalController} from '@ionic/angular';


/**
 * Generated class for the ImprimirPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */


@Component({
  selector: 'app-imprimir',
  templateUrl: './imprimir.page.html',
  styleUrls: ['./imprimir.page.scss'],
})
export class ImprimirPage {

  imprimirTexto:string = null;
  imprimirHTML: any;
  habilitaImprimir:boolean = false;


  /*
  let alert = this.alertController.create({
                header: 'Atención',
                message: 'Error al realizar la actualización de datos',
                buttons: [{
                  text: 'Aceptar'
                }
                ]
              }).then(res => {

                res.present();
              });
  
   */

  constructor(
              public alertCtrl: AlertController,
              private controlParametros: ControlParametrosService,
              public viewCtrl: ModalController,
              private printProvider:PrintProvider) {        
  }

  enviarImprimir(){
            let confirm = this.alertCtrl.create({
              header: 'Atención',
              message: '¿Esta seguró que desea Imprimir el Documento?',
              buttons: [
                      {
                        text: 'Si',
                          handler: () => {
                            this.printProvider.enviarImpresionTicket(""+this.imprimirHTML);
                          }
                      },
                     { text: 'No',  handler: () => { console.log('No selected!'); } }
                    ]
                    }).then(res => {
                      res.present();
            });
    } 

  ionViewDidEnter() {
    this.imprimirHTML =  this.controlParametros.getParametro('despacho_imp');
    console.log('ionViewDidLoad ImprimirPage');
  }

  regresar() {
    this.viewCtrl.dismiss();
  }

}
