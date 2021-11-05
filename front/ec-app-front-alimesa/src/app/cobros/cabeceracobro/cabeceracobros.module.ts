import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CabeceracobrosRoutingPageRoutingModule } from './cabeceracobros-routing.module';

import { CabeceracobrosPage } from './cabeceracobros.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    CabeceracobrosRoutingPageRoutingModule
  ],
  declarations: [CabeceracobrosPage]
})
export class CabeceracobrosPageModule {}
