import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { FlujodespachoPageRoutingModule } from './flujodespacho-routing.module';


import { FlujodespachoPage } from './flujosdespacho.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FlujodespachoPageRoutingModule
  ],
  declarations: [FlujodespachoPage]
})
export class FlujodespachoPageModule {}
