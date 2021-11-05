import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CrearclientePage } from './crearcliente.page';

const routes: Routes = [
  {
    path: '',
    component: CrearclientePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CrearclientePageRoutingModule {}
