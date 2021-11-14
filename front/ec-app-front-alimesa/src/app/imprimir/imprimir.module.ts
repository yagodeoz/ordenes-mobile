import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';
import { ImprimirPage } from './imprimir.page';
import { ImprimirPageRoutingModule } from './imprimir-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ImprimirPageRoutingModule
  ],
  declarations: [ImprimirPage]
})
export class ImprimirPageModule {}
