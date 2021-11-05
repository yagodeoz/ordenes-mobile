import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CabecerapedidosPage } from './cabecerapedidos.page';

const routes: Routes = [
  {
    path: '',
    component: CabecerapedidosPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CabecerapedidosPageRoutingModule {}
