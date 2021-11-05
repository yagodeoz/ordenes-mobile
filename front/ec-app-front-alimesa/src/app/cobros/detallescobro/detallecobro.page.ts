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
  selector: 'app-detallecobro',
  templateUrl: './detallecobro.page.html',
  styleUrls: ['./detallecobro.page.scss'],
})
export class DetalleCobroPage implements OnInit {

  comprobante: any;
  listaCobros : any;
  
  constructor(private utilMensaje: UtilmensajeService, private controlParametros: ControlParametrosService, private router: Router, public viewCtrl: ModalController,
              private controlAcceso: ControlaccesologinService, private loadingService: LoadingService, public alertController: AlertController,
              private tasksService: TasksServiceProvider
              ) { 

                this.comprobante = this.controlParametros.getParametro('comprobante');
                console.log (this.comprobante); 
                this.listaCobros =  this.controlParametros.getParametro('detalles'); 
                console.log (this.listaCobros); 

                 this.loadingService.loadingDismiss();   
              }

  ngOnInit() {   
  }
  
  noImplementado() {
    alert('No implementado por falta de definción');
  }

}
