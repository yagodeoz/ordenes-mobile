import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DetalleCobroPage } from './detallecobro.page';

const routes: Routes = [
  {
    path: '',
    component: DetalleCobroPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DetalleCobroPageRoutingModule {}
