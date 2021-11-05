import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { BuscarProductosDespachoPageRoutingModule } from './buscarproductosdespacho-routing.module';

import { BuscarProductosDespachoPage } from './buscarproductosdespacho.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    BuscarProductosDespachoPageRoutingModule
  ],
  declarations: [BuscarProductosDespachoPage]
})
export class BuscarProductosDespachoPageModule {}
