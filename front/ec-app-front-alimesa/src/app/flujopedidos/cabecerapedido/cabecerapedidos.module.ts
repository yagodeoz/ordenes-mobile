import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CabecerapedidosPageRoutingModule } from './cabecerapedidos-routing.module';

import { CabecerapedidosPage } from './cabecerapedidos.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    CabecerapedidosPageRoutingModule
  ],
  declarations: [CabecerapedidosPage]
})
export class CabecerapedidosPageModule {}
