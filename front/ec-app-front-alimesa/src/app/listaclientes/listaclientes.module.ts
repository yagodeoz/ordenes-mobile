import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ListaClientesPageRoutingModule } from './listaclientes-routing.module';

import { ListaclientesPage } from './listaclientes.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ListaClientesPageRoutingModule
  ],
  declarations: [ListaclientesPage]
})
export class ListaClientesPageModule {}
