import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ListaProductosPageRoutingModule } from './listaproductos-routing.module';

import { ListaproductosPage } from './listaproductos.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ListaProductosPageRoutingModule
  ],
  declarations: [ListaproductosPage]
})
export class ListaProductosPageModule {}
