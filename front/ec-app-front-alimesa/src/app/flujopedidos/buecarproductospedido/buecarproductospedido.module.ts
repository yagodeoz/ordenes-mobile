import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { BuecarproductospedidoPageRoutingModule } from './buecarproductospedido-routing.module';

import { BuecarproductospedidoPage } from './buecarproductospedido.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    BuecarproductospedidoPageRoutingModule
  ],
  declarations: [BuecarproductospedidoPage]
})
export class BuecarproductospedidoPageModule {}
