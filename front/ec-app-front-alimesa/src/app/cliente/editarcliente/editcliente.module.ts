import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EditclientePageRoutingModule } from './editcliente-routing.module';

import { EditclientePage } from './editcliente.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EditclientePageRoutingModule
  ],
  declarations: [EditclientePage]
})
export class EditclientePageModule {}
