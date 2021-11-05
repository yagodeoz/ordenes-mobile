import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { FlujopedidosPageRoutingModule } from './flujopedidos-routing.module';

import { FlujopedidosPage } from './flujopedidos.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FlujopedidosPageRoutingModule
  ],
  declarations: [FlujopedidosPage]
})
export class FlujopedidosPageModule {}