import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BuecarproductospedidoPage } from './buecarproductospedido.page';

const routes: Routes = [
  {
    path: '',
    component: BuecarproductospedidoPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BuecarproductospedidoPageRoutingModule {}
