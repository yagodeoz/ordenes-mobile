import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CabeceracobrosPage } from './cabeceracobros.page';

const routes: Routes = [
  {
    path: '',
    component: CabeceracobrosPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CabeceracobrosRoutingPageRoutingModule {}
