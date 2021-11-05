import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CabeceracobrobancoPageRoutingModule } from './cabeceracobrobanco-routing.module';

import { CabeceracobrobancoPage } from './cabeceracobrobanco.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    CabeceracobrobancoPageRoutingModule
  ],
  declarations: [CabeceracobrobancoPage]
})
export class CabeceracobrobancoPageModule {}
