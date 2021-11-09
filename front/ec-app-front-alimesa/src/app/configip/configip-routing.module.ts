import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ConfigipPage } from './configip.page';

const routes: Routes = [
  {
    path: '',
    component: ConfigipPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ConfigipPageRoutingModule {}
