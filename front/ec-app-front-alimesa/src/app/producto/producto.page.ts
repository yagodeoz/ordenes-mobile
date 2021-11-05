import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ControlParametrosService} from "../servicios/transversales/control-parametros.service";

@Component({
  selector: 'app-producto',
  templateUrl: './producto.page.html',
  styleUrls: ['./producto.page.scss'],
})
export class ProductoPage implements OnInit {

  item: any;

  constructor(private controlParametros: ControlParametrosService) {
  }

  ngOnInit() {
    this.item = this.controlParametros.getParametro('prod_item');
  }

  procesarOrden(){
    alert('En construcción por falta de definción');
  }

}
