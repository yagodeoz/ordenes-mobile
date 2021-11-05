import { Component, OnInit } from '@angular/core';
import {ControlParametrosService} from "../servicios/transversales/control-parametros.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.page.html',
  styleUrls: ['./cliente.page.scss'],
})
export class ClientePage implements OnInit {


  item: any;

  constructor(private controlParametros: ControlParametrosService, private router: Router) { }

  ngOnInit() {
    this.item = this.controlParametros.getParametro('cliente_item');
  }

  noImplementado() {
    alert('No implementado por falta de definción');
  }




  editarLocalizcion() {

    this.router.navigate(['/geolocalizacion'] );
  }

  editarCliente() {

    this.router.navigate(['/editcliente'] );
  }

}
