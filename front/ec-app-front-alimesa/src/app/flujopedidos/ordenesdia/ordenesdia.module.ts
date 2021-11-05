import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { OrdenesdiaPageRoutingModule } from './ordenesdia-routing.module';

import { OrdenesdiaPage } from './ordenesdia.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    OrdenesdiaPageRoutingModule
  ],
  declarations: [OrdenesdiaPage]
})
export class OrdenesdiaPageModule {}
