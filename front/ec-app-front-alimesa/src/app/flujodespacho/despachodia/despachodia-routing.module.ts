import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DespachodiaPage } from './despachodia.page';

const routes: Routes = [
  {
    path: '',
    component: DespachodiaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DespachodiaPageRoutingModule {}
