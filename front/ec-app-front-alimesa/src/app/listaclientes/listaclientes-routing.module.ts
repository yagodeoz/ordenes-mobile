import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListaclientesPage } from './listaclientes.page';

const routes: Routes = [
  {
    path: '',
    component: ListaclientesPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ListaClientesPageRoutingModule {}
