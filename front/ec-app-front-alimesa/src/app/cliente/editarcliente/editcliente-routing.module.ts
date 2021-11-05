import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EditclientePage } from './editcliente.page';

const routes: Routes = [
  {
    path: '',
    component: EditclientePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EditclientePageRoutingModule {}
