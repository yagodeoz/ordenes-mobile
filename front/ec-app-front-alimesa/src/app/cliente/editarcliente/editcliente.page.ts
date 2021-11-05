import { Component, OnInit } from '@angular/core';
import {ControlParametrosService} from '../../servicios/transversales/control-parametros.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-editcliente',
  templateUrl: './editcliente.page.html',
  styleUrls: ['./editcliente.page.scss'],
})
export class EditclientePage implements OnInit {

  item: any;

  constructor(private controlParametros: ControlParametrosService, private router: Router) { }

  ngOnInit() {
    this.item = this.controlParametros.getParametro('cliente_item');
  }

  procesarEdicion(){
    alert('Pendiente integración MBA');
    this.router.navigate(['/cliente'] );
  }

}
