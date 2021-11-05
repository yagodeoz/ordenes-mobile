import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BuscarProductosDespachoPage } from './buscarproductosdespacho.page';

const routes: Routes = [
  {
    path: '',
    component: BuscarProductosDespachoPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BuscarProductosDespachoPageRoutingModule {}
