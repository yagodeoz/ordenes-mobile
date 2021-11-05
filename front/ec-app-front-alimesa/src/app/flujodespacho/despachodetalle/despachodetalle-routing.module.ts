import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DespachodetallePage } from './despachodetalle.page';

const routes: Routes = [
  {
    path: '',
    component: DespachodetallePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DespachodetallePageRoutingModule {}
