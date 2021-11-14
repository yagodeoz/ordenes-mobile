import { Injectable } from '@angular/core';
import { AlertController } from '@ionic/angular';
import { LoadingService } from '../servicios/transversales/loading.service';
import { BluetoothSerial } from '@ionic-native/bluetooth-serial/ngx';



/*
  Generated class for the PrintProvider provider.
  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class PrintProvider {

  selectedPrinter: any = [];

  constructor(private alertController: AlertController, private loadingService: LoadingService, private btSerial: BluetoothSerial) {

  }

  searchBt() {

    console.log("BUSCANDO IMPRESORAS");

    return this.btSerial.list();
  }

  connectBT(address) {
    return this.btSerial.connect(address);

  }


  //*** ENVIO DE IMPRESION DE TICKET - IMPRESORA BLUETOOTH ****
  enviarImpresionTicket(texto: string) {
    console.log("Inicio - enviarImpresionTicket");
    //**************************************************************
    this.loadingService.loadingPresent('Imprimiendo... <br><b>Por favor espere...</b>');
    //Bloques de Impresion
    this.connectPrinter()
      .then(() => this.enviarImpresoraBT(this.selectedPrinter.id, texto))
      //Cierre Espera
      .then(() => this.loadingService.loadingDismiss());
    //**************************************************************
    console.log("Fin - enviarImpresionTicket");
  }
  //************************************************************


  //***** SELECCION DE LA IMPRESORA *****************
  connectPrinter() {
    return new Promise((resolve, reject) => {
        this.searchBt().then(datalist => {
           console.log("LISTA DE IMPRESORAS DEVUELTAS " + datalist.length);
          for (let i = 0; i < datalist.length; i++) {
              console.log("NOMBRE IMPRESORA " + datalist[i].name);            
              this.selectedPrinter = datalist[i];            
          }
          resolve(this.selectedPrinter);
        }, err => {
          this.loadingService.loadingDismiss()
          console.log("ERROR", err);
          reject(err);
        });
    })
  }

  ///************************************************


  //*** ENVIO DE IMPRESION DE TICKET - IMPRESORA BLUETOOTH ****
  enviarImpresoraBT(address, texto: string) {
    if (address == null || address == "" || address == undefined) {


      let alert = this.alertController.create({
        header: 'ERROR',
        message: 'No existe comunicación con la Impresora.',
        buttons: [{
          text: 'Aceptar'
        }
        ]
      }).then(res => {

        res.present();
      });

    } else {
      let printData = texto; //"Test hello this is a test \n\n\n\n Hello Test 123 123 123\n\n\n"    
      let xyz = this.connectBT(address).subscribe(data => {
        this.btSerial.write(printData).then(dataz => {
          console.log("WRITE SUCCESS", dataz);

          let alert = this.alertController.create({
            header: 'Información',
            message: 'Impresión OK',
            buttons: [{
              text: 'Aceptar'
            }
            ]
          }).then(res => {
            res.present();
          });
          xyz.unsubscribe();
        }, errx => {
          this.loadingService.loadingDismiss()
          console.log("WRITE FAILED", errx);
          let alert = this.alertController.create({
            header: 'Error',
            message: 'Mensaje: ' + errx,
            buttons: [{
              text: 'Aceptar'
            }
            ]
          }).then(res => {
            res.present();
          });

        });
      }, err => {
        this.loadingService.loadingDismiss()
        console.log("CONNECTION ERROR", err);

        let alert = this.alertController.create({
          header: 'Error',
          message: 'Mensaje: ' + err,
          buttons: [{
            text: 'Aceptar'
          }
          ]
        }).then(res => {
          res.present();
        });
      });
    }
  }
  //********************************************************


}