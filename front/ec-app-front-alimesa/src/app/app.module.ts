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

import {BuscarProductosDespachoPage} from './flujodespacho/buscarproductosdespacho/buscarproductosdespacho.page';
import {BuscarProductosDespachoPageModule} from './flujodespacho/buscarproductosdespacho/buscarproductosdespacho.module';

@NgModule({
  declarations: [AppComponent],
  entryComponents: [BuecarproductospedidoPage, BuscarProductosDespachoPage],
  imports: [
    BrowserModule,
    IonicModule.forRoot(),
    IonicStorageModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
    BuecarproductospedidoPageModule,
    BuscarProductosDespachoPageModule
  ],
  providers: [
    StatusBar,
    SplashScreen,
    MessagesProvider,
    AuthGuardService,
    AuthenticationService,
    TasksServiceProvider,
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy },
    SQLite,
      SQLitePorter,
      { 
        provide: RouteReuseStrategy, 
        useClass: IonicRouteStrategy 
      }     
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

  constructor() {
  }

}
