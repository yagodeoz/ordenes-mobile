import { Component, OnInit } from '@angular/core';
import {LoadingService} from '../servicios/transversales/loading.service';
import {ProductosService} from '../servicios/ventas/productos/productos.service';
import {UtilmensajeService} from '../servicios/transversales/utilmensaje.service';
import {Router} from '@angular/router';
import {ControlParametrosService} from '../servicios/transversales/control-parametros.service';
import {ModalController} from '@ionic/angular';
import { TasksServiceProvider } from '../providers/tasks-service';

@Component({
  selector: 'app-configip',
  templateUrl: './configip.page.html',
  styleUrls: ['./configip.page.scss'],
})
export class ConfigipPage implements OnInit {

  urlApiGateWay

  constructor(private loadingService: LoadingService, private servicioProducto: ProductosService,
              private utilMensaje: UtilmensajeService, private router: Router,
              private controlParametros: ControlParametrosService, public viewCtrl: ModalController, private taskService : TasksServiceProvider) { 

               this.urlApiGateWay =   this.controlParametros.getParametro('ip_api');              
              
              }

  ngOnInit() {
  }

  registrarIp(){

      if (this.urlApiGateWay.length <= 0 ){

        return;
      }
      
       this.loadingService.loadingPresent('Registrando URL...');
      
        let sqlGeneral = 'INSERT OR REPLACE INTO CONFIG (id, ipapigateway) VALUES( 1 , \"' + this.urlApiGateWay + '\");';
        let sqlInsert: Array<string> = [];
        sqlInsert.push(sqlGeneral);
        this.taskService.db.sqlBatch(sqlInsert)
          .then(() => {
              this.loadingService.loadingDismiss();
              this.viewCtrl.dismiss();
          });
  }

   regresar() {
    this.viewCtrl.dismiss();
  }
 
}   
