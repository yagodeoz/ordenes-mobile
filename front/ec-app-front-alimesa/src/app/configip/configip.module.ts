import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ConfigipPageRoutingModule } from './configip-routing.module';

import { ConfigipPage } from './configip.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ConfigipPageRoutingModule
  ],
  declarations: [ConfigipPage]
})
export class ConfigipPageModule {}
