import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CabeceradespachoPageRoutingModule } from './cabeceradespachos-routing.module';

import { CabeceradespachoPage } from './cabeceradespachos.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    CabeceradespachoPageRoutingModule
  ],
  declarations: [CabeceradespachoPage]
})
export class CabeceradespachoPageModule {}
