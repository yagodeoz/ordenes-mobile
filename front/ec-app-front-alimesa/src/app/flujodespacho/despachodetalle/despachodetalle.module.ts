import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { DespachodetallePageRoutingModule } from './despachodetalle-routing.module';

import { DespachodetallePage } from './despachodetalle.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    DespachodetallePageRoutingModule
  ],
  declarations: [DespachodetallePage]
})
export class DespachodetallePageModule {}
