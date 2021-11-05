import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { DespachodiaPageRoutingModule } from './despachodia-routing.module';


import { DespachodiaPage } from './despachodia.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    DespachodiaPageRoutingModule
  ],
  declarations: [DespachodiaPage]
})
export class DespachodiaPageModule {}
