import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { DetalleCobroPageRoutingModule } from './detallecobro-routing.module';

import { DetalleCobroPage } from './detallecobro.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    DetalleCobroPageRoutingModule
  ],
  declarations: [DetalleCobroPage]
})
export class DetalleCobroPageModule {}
