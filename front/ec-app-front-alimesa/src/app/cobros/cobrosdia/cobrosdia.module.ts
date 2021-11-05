import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CobrosdiaPageRoutingModule } from './cobrosdia-routing.module';

import { CobrosdiaPage } from './cobrosdia.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    CobrosdiaPageRoutingModule
  ],
  declarations: [CobrosdiaPage]
})
export class CobrosdiaPageModule {}
