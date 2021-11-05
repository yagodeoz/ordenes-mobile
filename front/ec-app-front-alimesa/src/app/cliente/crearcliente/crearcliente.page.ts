import { Component, OnInit } from '@angular/core';
import {ControlParametrosService} from '../../servicios/transversales/control-parametros.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-crearcliente',
  templateUrl: './crearcliente.page.html',
  styleUrls: ['./crearcliente.page.scss'],
})
export class CrearclientePage implements OnInit {

  constructor(private controlParametros: ControlParametrosService, private router: Router) { }

  ngOnInit() {
  }

  procesarEdicion() {
    alert('Pendiente integración MBA');
  }

}
