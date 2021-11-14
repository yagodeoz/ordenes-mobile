import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import {MessagesProvider} from './messages/messages';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import {AuthenticationService} from './servicios/authentication.service';
import { HttpClientModule} from '@angular/common/http';
import {AuthGuardService} from './servicios/authGuard.service';

import { IonicStorageModule } from '@ionic/storage';
import {BuecarproductospedidoPage} from './flujopedidos/buecarproductospedido/buecarproductospedido.page';
import {BuecarproductospedidoPageModule} from './flujopedidos/buecarproductospedido/buecarproductospedido.module';
import { SQLite } from '@ionic-native/sqlite/ngx';
import { SQLitePorter } from '@ionic-native/sqlite-porter/ngx';
import { TasksServiceProvider } from './providers/tasks-service';
import { PrintProvider } from './providers/print-service';

import {BuscarProductosDespachoPage} from './flujodespacho/buscarproductosdespacho/buscarproductosdespacho.page';
import {BuscarProductosDespachoPageModule} from './flujodespacho/buscarproductosdespacho/buscarproductosdespacho.module';
import { ConfigipPage } from './configip/configip.page';
import { ConfigipPageModule } from './configip/configip.module';

import { BluetoothSerial } from '@ionic-native/bluetooth-serial/ngx';

import { ImprimirPage } from './imprimir/imprimir.page';
import { ImprimirPageModule } from './imprimir/imprimir.module';

import { DatePipe } from '@angular/common';
import { DecimalPipe } from '@angular/common';
import { CurrencyPipe } from '@angular/common';

@NgModule({
  declarations: [AppComponent],
  entryComponents: [BuecarproductospedidoPage, BuscarProductosDespachoPage, ConfigipPage, ImprimirPage],
  imports: [
    BrowserModule,
    IonicModule.forRoot(),
    IonicStorageModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
    BuecarproductospedidoPageModule,
    BuscarProductosDespachoPageModule,
    ConfigipPageModule,
    ImprimirPageModule
  ],
  providers: [
    DatePipe,
    DecimalPipe,
    CurrencyPipe,
    StatusBar,
    SplashScreen,
    MessagesProvider,
    AuthGuardService,
    AuthenticationService,
    TasksServiceProvider,
    PrintProvider,
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy },
    SQLite,
      SQLitePorter,
      { 
        provide: RouteReuseStrategy, 
        useClass: IonicRouteStrategy 
      },
    BluetoothSerial,      
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

  constructor() {
  }

}
