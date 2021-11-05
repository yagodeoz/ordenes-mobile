import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { OrdenesdiaPage } from './ordenesdia.page';

const routes: Routes = [
  {
    path: '',
    component: OrdenesdiaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OrdenesdiaPageRoutingModule {}
