import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CabeceradespachoPage } from './cabeceradespachos.page';

const routes: Routes = [
  {
    path: '',
    component: CabeceradespachoPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CabeceradespachoPageRoutingModule {}
